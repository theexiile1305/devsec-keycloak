package dev.mfuchs.devseckeycloak.repository

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "notes")
data class NoteEntity(

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "notes_id_sequence"
    )
    @SequenceGenerator(
        name = "notes_id_sequence",
        sequenceName = "notes_id_seq",
        allocationSize = 1
    )
    val id: Long = 0,

    @Column(nullable = false)
    val uuid: UUID,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String
)