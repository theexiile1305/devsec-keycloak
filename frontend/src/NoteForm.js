import React, { useState, useEffect, useRef } from 'react';

function NoteForm(props) {

    const [input, setInput] = useState(props.edit ? props.edit.value : '');

    const inputRef = useRef(null);

    useEffect(() => {
        inputRef.current.focus();
    });

    const handleChange = e => {
        setInput(e.target.value);
    };

    const handleSubmit = e => {
        e.preventDefault();

        props.onSubmit({
            uuid: Math.floor(Math.random() * 10000),
            text: input
        });
        setInput('');
    };

    return (
        <form onSubmit={handleSubmit} className='note-form'>
            {props.edit ? (
                <>
                    <input
                        placeholder='Update your note'
                        value={input}
                        onChange={handleChange}
                        name='text'
                        ref={inputRef}
                        className='note-input edit'
                    />
                    <button onClick={handleSubmit} className='note-button edit'>Update</button>
                </>
            ) : (
                <>
                    <input
                        placeholder='Add a note'
                        value={input}
                        onChange={handleChange}
                        name='text'
                        className='note-input'
                        ref={inputRef}
                    />
                    <button onClick={handleSubmit} className='note-button'>Add note</button>
                </>
            )}
        </form>
    );
}

export default NoteForm;