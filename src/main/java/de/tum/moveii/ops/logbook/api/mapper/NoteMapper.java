package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alert.model.Note;
import de.tum.moveii.ops.logbook.api.message.NoteMessage;
import org.springframework.stereotype.Component;

/**
 * Created by Constantin Costescu on 12-Feb-17.
 */
@Component
public class NoteMapper implements ResourceMapper<NoteMessage, Note> {
    @Override
    public Note toResource(NoteMessage message) {
        return Note.builder()
                .noteId(message.getNoteId())
                .message(message.getMessage())
                .ownerId(message.getOwner())
                .createdOn(message.getCreatedOn())
                .build();
    }

    @Override
    public NoteMessage toMessage(Note resource) {
        return NoteMessage.builder()
                .noteId(resource.getNoteId())
                .message(resource.getMessage())
                .owner(resource.getOwnerId())
                .createdOn(resource.getCreatedOn())
                .build();
    }
}
