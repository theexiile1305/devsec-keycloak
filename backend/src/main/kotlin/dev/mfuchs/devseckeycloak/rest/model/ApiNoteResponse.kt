package dev.mfuchs.devseckeycloak.rest.model

import dev.mfuchs.devseckeycloak.service.Note
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(title = "NoteResponse")
data class ApiNoteResponse(

    @Schema(required = true, example = "123e4567-e89b-12d3-a456-426655440000")
    val uuid: UUID,

    @Schema(required = true, example = "Some Title")
    val title: String,

    @Schema(required = true, example = "Some Description")
    val description: String
) {
    companion object {
        fun toApiNote(
            note: Note
        ): ApiNoteResponse = ApiNoteResponse(
            uuid = note.uuid,
            title = note.title,
            description = note.description
        )
    }
}