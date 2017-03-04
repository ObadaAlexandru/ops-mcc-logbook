package de.tum.moveii.ops.logbook.alert.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "NOTES", schema = "LOGBOOK")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "noteId")
@Builder
public class Note {
    @Id
    @SequenceGenerator(name = "notes_noteId_seq",
            sequenceName = "notes_noteId_seq",
            schema = "LOGBOOK",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "NOTE_ID", nullable = false, updatable = false)
    private Long noteId;

    @Column(name = "AUTHOR_ID", nullable = false)
    private Long ownerId;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "ALERT_ID")
    private Alert alert;
}
