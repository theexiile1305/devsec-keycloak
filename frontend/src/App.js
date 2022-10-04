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
        localStorage.setItem("private", "true")
    };

    const home = () => {
        console.log("Home")
        localStorage.removeItem("private")
        window.location.reload();
    };

    const content = () => {
        console.log(localStorage.getItem("private"))
        if(localStorage.getItem("private")){
            return <div>
                <h1>Private Area</h1>
                <p>See secret stuff here...</p>
            </div>
        } else {
            return <NoteList />
        }
    }

    return (
        <div className="note-app">
            <div className="note-button" onClick={home}>
                Home
            </div>
            <div className="note-button" onClick={stepUp}>
                Private Area
            </div>
            <div className="note-button logout" onClick={logout}>
                Logout
            </div>
            { content() }
        </div>
    );
}

export default App;
