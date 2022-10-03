package dev.mfuchs.devseckeycloak.rest.model

import dev.mfuchs.devseckeycloak.service.Note
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(title = "NoteRequest")
data class ApiNoteRequest(

    @Schema(required = true, example = "Some Text")
    val text: String
) {
    companion object {
        fun toNote(
            apiNote: ApiNoteRequest
        ): Note = Note(
            uuid = UUID.randomUUID(),
            text = apiNote.text
        )
    }
}