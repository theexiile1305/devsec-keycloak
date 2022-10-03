import React, { useState } from 'react';
import NoteForm from './NoteForm';
import { RiCloseCircleLine } from 'react-icons/ri';
import { TiEdit } from 'react-icons/ti';

const Note = ({ notes, removeNote, updateNote }) => {

    const [edit, setEdit] = useState({
        uuid: null,
        value: ''
    });

    const submitUpdate = value => {
        updateNote(edit.uuid, value);
        setEdit({
            uuid: null,
            value: ''
        });
    };

    if (edit.uuid) {
        return <NoteForm edit={edit} onSubmit={submitUpdate} />;
    }

    return notes.map((note, index) => (
        <div className='note-row' key={index}>
            <div key={note.uuid}>{note.text}</div>
            <div className='icons'>
                <RiCloseCircleLine
                    onClick={() => removeNote(note.uuid)}
                    className='delete-icon'
                />
                <TiEdit
                    onClick={() => setEdit({ uuid: note.uuid, value: note.text })}
                    className='edit-icon'
                />
            </div>
        </div>
    ));
};

export default Note;