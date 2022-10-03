import React, {useEffect, useState} from 'react';
import NoteForm from './NoteForm';
import Note from './Note';
import axios from "axios";

function NoteList() {

    const instance = axios.create({
        baseURL: 'https://backend.devsec-keycloak.mfuchs.dev/notes',
        timeout: 1000,
        headers: {
            'accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('react-token')
        }
    });

    const [notes, setNotes] = useState([]);

    useEffect(() => {
        instance.get('')
            .then(response => setNotes(response.data.notes))
            .catch(error => console.error(error));
    }, [instance]);

    const addNote = note => {
        if (!note.text || /^\s*$/.test(note.text)) return;

        instance.post('', {text: note.text})
            .then(() => instance.get(''))
            .then(response => setNotes(response.data.notes))
            .catch(error => console.error(error));
    };

    const removeNote = uuid => {
        instance.delete("/" + uuid)
            .then(() => instance.get(''))
            .then(response => setNotes(response.data.notes))
            .catch(error => console.error(error));
    };

    const updateNote = (uuid, newValue) => {
        if (!newValue.text || /^\s*$/.test(newValue.text)) return;

        instance.put("/" + uuid, {text: newValue.text})
            .then(() => instance.get(''))
            .then(response => setNotes(response.data.notes))
            .catch(error => console.error(error));
    };

    return (
        <>
            <h1>What's your notes for today?</h1>
            <NoteForm onSubmit={addNote}/>
            <Note
                notes={notes}
                removeNote={removeNote}
                updateNote={updateNote}
            />
        </>
    );
}

export default NoteList;