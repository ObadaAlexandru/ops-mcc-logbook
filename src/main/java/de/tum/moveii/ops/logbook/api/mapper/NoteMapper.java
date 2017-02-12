package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alarm.model.Note;
import de.tum.moveii.ops.logbook.api.message.NoteMessage;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by Constantin Costescu on 12-Feb-17.
 */
@Component
public class NoteMapper implements ResourceMapper<NoteMessage, Note> {
    @Override
    public Note toResource(NoteMessage message) {
        Note note = Note.builder()
                .message(message.getMessage())
                .ownerId(message.getOwner())
                .createdOn(Timestamp.valueOf(message.getCreatedOn()))
                .build();

        return note;
    }

    @Override
    public NoteMessage toMessage(Note resource) {
        NoteMessage noteMessage = NoteMessage.builder()
                .message(resource.getMessage())
                .owner(resource.getOwnerId())
                .createdOn(resource.getCreatedOn().toLocalDateTime())
                .build();

        return noteMessage;
    }
}
