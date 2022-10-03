package dev.mfuchs.devseckeycloak.rest.model

import dev.mfuchs.devseckeycloak.rest.model.ApiNoteResponse.Companion.toApiNote
import dev.mfuchs.devseckeycloak.service.Note
import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Notes")
data class ApiNotes(

    @Schema(required = true)
    val notes: List<ApiNoteResponse>
) {
    companion object {
        fun toApiNotes(
            notes: List<Note>
        ): ApiNotes = ApiNotes(
            notes = notes.map { toApiNote(it) }
        )
    }
}