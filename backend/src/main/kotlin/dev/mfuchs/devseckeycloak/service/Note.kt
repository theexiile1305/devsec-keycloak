package dev.mfuchs.devseckeycloak.service

import dev.mfuchs.devseckeycloak.repository.NoteEntity
import java.util.UUID

data class Note(
    val uuid: UUID,
    val title: String,
    val description: String
) {
    companion object {
        fun toNoteEntity(
            note: Note
        ): NoteEntity = NoteEntity(
            uuid = note.uuid,
            title = note.title,
            description = note.description
        )

        fun toNote(
            noteEntity: NoteEntity
        ): Note = Note(
            uuid = noteEntity.uuid,
            title = noteEntity.title,
            description = noteEntity.description
        )
    }
}