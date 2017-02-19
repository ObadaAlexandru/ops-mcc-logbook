import de.tum.moveii.ops.logbook.alert.model.Note
import de.tum.moveii.ops.logbook.api.mapper.NoteMapper
import de.tum.moveii.ops.logbook.api.message.NoteMessage
import spock.lang.Specification

import java.sql.Timestamp

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class NoteMapperTest extends Specification {

    def noteMapper = new NoteMapper()

    def 'Map note to noteMessage'() {
        given:
        def noteId = 1L
        def note = new Note(noteId, 2L, "test_msg", Timestamp.valueOf("2016-02-19 10:35:30"), null)

        def noteMessage = new NoteMessage(noteId, 2L, "test_msg",
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime())
        when:
        def actualNoteMessage = noteMapper.toMessage(note)
        then:
        actualNoteMessage == noteMessage
    }

    def 'Map noteMessage to note'() {
        given:
        def noteId = 1L
        def note = new Note(noteId, 2L, "test_msg", Timestamp.valueOf("2016-02-19 10:35:30"), null)

        def noteMessage = new NoteMessage(noteId, 2L, "test_msg",
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime())
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
