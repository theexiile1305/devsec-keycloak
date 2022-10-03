package dev.mfuchs.devseckeycloak.service

import dev.mfuchs.devseckeycloak.repository.NoteEntity
import java.util.UUID

data class Note(
    val uuid: UUID,
    val text: String
) {
    companion object {
        fun toNoteEntity(
            note: Note
        ): NoteEntity = NoteEntity(
            uuid = note.uuid,
            text = note.text
        )

        fun toNote(
            noteEntity: NoteEntity
        ): Note = Note(
            uuid = noteEntity.uuid,
            text = noteEntity.text
        )
    }
}