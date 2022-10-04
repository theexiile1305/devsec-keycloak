import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: 'https://keycloak.devsec-keycloak.mfuchs.dev/auth',
    realm: 'keycloak-demo',
    clientId: 'app-react',
    onLoad: 'login-required'
});

keycloak
    .init({ onLoad: "login-required" })
    .then(authenticated => {
        if (!authenticated) window.location.reload();
        else
            console.info("Authenticated!")

        ReactDOM
            .createRoot(document.getElementById('root'))
            .render(<React.StrictMode><App keycloak={keycloak}></App></React.StrictMode>)

        localStorage.setItem("react-token", keycloak.token);
        localStorage.setItem("react-refresh-token", keycloak.refreshToken);

        setTimeout(() => {
            keycloak
                .updateToken(70)
                .then(refreshed => {
                    if (refreshed)
                        console.debug("Token refreshed " + refreshed);
                    else
                        console.warn(
                            "Token not refreshed, valid for "
                            + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000)
                            + " seconds"
                        );
                }).catch(reason => {
                console.error("Failed to refresh token, cause of " + reason)
            })
        }, 6000);
    }).catch(reason => {
    console.error("Authenticated failed, cause of " + reason)
});
