import './App.css';
import NoteList from "./NoteList";

function App({keycloak}) {
    const logout = () => {
        console.log("Logout")
        keycloak.logout()
            .then(res => {
                window.location.reload();
            });
    };

    const stepUp = () => {
        console.log("Step up")
        keycloak.login({
            acr: {
                essential: true,
                values: ["private"]
            }
        })
            .then(res => {
                window.location.reload();
            });
    };

    return (
        <div className="note-app">
            <div className="note-button" onClick={stepUp}>
                Private Area
            </div>
            <div className="note-button logout" onClick={logout}>
                Logout
            </div>Okay
            <NoteList />
        </div>
    );
}

export default App;
