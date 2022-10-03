package dev.mfuchs.devseckeycloak.service

import dev.mfuchs.devseckeycloak.service.Note.Companion.toNote
import dev.mfuchs.devseckeycloak.service.Note.Companion.toNoteEntity
import dev.mfuchs.devseckeycloak.repository.NoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class NoteService(
    private val noteRepository: NoteRepository
) {
    @Transactional
    fun createNote(note: Note): Note = toNote(noteRepository.save(toNoteEntity(note)))

    @Transactional(readOnly = true)
    fun getNote(noteUuid: UUID): Note? = noteRepository.findByUuid(noteUuid)?.let { toNote(it) }

    @Transactional(readOnly = true)
    fun getNotes(): List<Note> = noteRepository.findAll().map { toNote(it) }

    @Transactional
    fun updateNote(noteUuid: UUID, note: Note): Note? = noteRepository.findByUuid(noteUuid)?.let {
        toNote(noteRepository.save(it.copy(title = note.title, description = note.description)))
    }

    @Transactional
    fun deleteNote(noteUuid: UUID): Unit = noteRepository.deleteByUuid(noteUuid)
}