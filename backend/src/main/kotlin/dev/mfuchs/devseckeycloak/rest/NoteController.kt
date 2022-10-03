package dev.mfuchs.devseckeycloak.rest

import dev.mfuchs.devseckeycloak.rest.model.ApiNoteRequest.Companion.toNote
import dev.mfuchs.devseckeycloak.rest.model.ApiNoteResponse.Companion.toApiNote
import dev.mfuchs.devseckeycloak.configuration.NotesReadAuthorization
import dev.mfuchs.devseckeycloak.configuration.NotesWriteAuthorization
import dev.mfuchs.devseckeycloak.rest.model.ApiNotes.Companion.toApiNotes
import dev.mfuchs.devseckeycloak.rest.NoteController.Companion.TAG
import dev.mfuchs.devseckeycloak.rest.model.ApiNoteRequest
import dev.mfuchs.devseckeycloak.rest.model.ApiNoteResponse
import dev.mfuchs.devseckeycloak.rest.model.ApiNotes
import dev.mfuchs.devseckeycloak.service.NoteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID
import javax.validation.Valid

@Tag(name = TAG)
@RestController
@RequestMapping(
    value = ["/notes"],
    produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE]
)
@Validated
class NoteController(
    private val noteService: NoteService
) {

    companion object {
        const val TAG = "notes"
        const val DESCRIPTION = "Resource for notes with all CRUD-Operations"
    }

    @Operation(summary = "Creation of a new note.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = [RequestMethod.POST])
    @NotesWriteAuthorization
    fun createNote(
        @Valid @RequestBody apiNote: ApiNoteRequest
    ): ApiNoteResponse = toApiNote(noteService.createNote(note = toNote(apiNote)))

    @Operation(summary = "Reading of of a specific note with given UUID.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = [RequestMethod.GET], value = ["/{note-uuid}"])
    @NotesReadAuthorization
    fun getNote(
        @PathVariable("note-uuid")
        @Parameter(
            name = "note-uuid",
            description = "UUID of the specific note",
            required = true,
            example = "a12d0f49-8d34-49c6-808f-fa1203b18147"
        )
        noteUuid: String
    ): ApiNoteResponse? = noteService.getNote(
        noteUuid = UUID.fromString(noteUuid)
    )?.let { toApiNote(it) }

    @Operation(summary = "Reading of of a all notes.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = [RequestMethod.GET])
    @NotesReadAuthorization
    fun getNotes(
    ): ApiNotes = toApiNotes(noteService.getNotes())

    @Operation(summary = "Update of of a specific note with given UUID.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = [RequestMethod.PUT], value = ["/{note-uuid}"])
    @NotesWriteAuthorization
    fun updateNote(
        @PathVariable("note-uuid")
        @Parameter(
            name = "note-uuid",
            description = "UUID of the specific note",
            required = true,
            example = "a12d0f49-8d34-49c6-808f-fa1203b18147"
        )
         noteUuid: String,

        @Valid @RequestBody apiNote: ApiNoteRequest
    ): ApiNoteResponse? = noteService.updateNote(
        noteUuid = UUID.fromString(noteUuid),
        note = toNote(apiNote)
    )?.let { toApiNote(it) }

    @Operation(summary = "Deletion of of a specific note with given UUID.")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = [RequestMethod.DELETE], value = ["/{note-uuid}"])
    @NotesWriteAuthorization
    fun deleteNote(
        @PathVariable("note-uuid")
        @Parameter(
            name = "note-uuid",
            description = "UUID of the specific note",
            required = true,
            example = "a12d0f49-8d34-49c6-808f-fa1203b18147"
        )
        noteUuid: String
    ): Unit = noteService.deleteNote(noteUuid = UUID.fromString(noteUuid))
}