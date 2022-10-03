package dev.mfuchs.devseckeycloak.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NoteRepository : CrudRepository<NoteEntity, Long> {

    fun findByUuid(uuid: UUID): NoteEntity?

    fun deleteByUuid(noteUuid: UUID)
}