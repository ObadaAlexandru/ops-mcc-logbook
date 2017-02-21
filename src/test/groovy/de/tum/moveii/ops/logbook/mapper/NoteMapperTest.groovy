import de.tum.moveii.ops.logbook.alert.model.Note
import de.tum.moveii.ops.logbook.api.mapper.NoteMapper
import de.tum.moveii.ops.logbook.api.message.NoteMessage
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class NoteMapperTest extends Specification {

    def noteMapper = new NoteMapper()

    def 'Map note to noteMessage'() {
        given:
        def noteId = 1L
        def note = Note.builder()
                .noteId(noteId)
                .ownerId(2L)
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def noteMessage = NoteMessage.builder()
                .noteId(noteId)
                .owner(2L)
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
        when:
        def actualNoteMessage = noteMapper.toMessage(note)
        then:
        actualNoteMessage == noteMessage
    }

    def 'Map noteMessage to note'() {
        given:
        def noteId = 1L
        def note = Note.builder()
                .noteId(noteId)
                .ownerId(2L)
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def noteMessage = NoteMessage.builder()
                .noteId(noteId)
                .owner(2L)
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
        when:
        def actualNote = noteMapper.toResource(noteMessage)
        then:
        actualNote == note
    }

    def 'Null note'() {
        when:
        noteMapper.toMessage(null)
        then:
        thrown(NullPointerException)
    }

    def 'Null noteMessage'() {
        when:
        noteMapper.toResource(null)
        then:
        thrown(NullPointerException)
    }
}
