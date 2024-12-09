import note.Note
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NoteServiceTest {

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addNotes() {
        val service = NoteService
        val note = service.add("test add notes")

        assertEquals(note.content, "test add notes")
    }

    @Test
    fun editValidNotes() {
        val service = NoteService
        val note = service.add("test add notes")
        service.edit(note.id, "test edit notes")

        assertEquals(note.content,"test edit notes")
    }

    @Test
    fun throwExceptionWhenEditNotesWithNotFoundId() {

        assertThrows(Exception::class.java) {
            NoteService.edit(1, "test edit notes")
        }
    }

    @Test
    fun throwExceptionWhenEditNotesWithRemovedId() {
        val service = NoteService
        var note = service.add("test add notes")
        service.delete(note.id)

        assertThrows(Exception::class.java) {
            service.edit(note.id, "test edit notes")
        }
    }

    @Test
    fun deleteValidNotes() {
        val service = NoteService
        val note = service.add("test add notes")
        service.delete(note.id)

        assertEquals(note.isDeleted, true)
    }

    @Test
    fun ThrowExceptionWhenDeleteNotesWithNotFoundId() {

        assertThrows(Exception::class.java) {
            NoteService.delete(1)
        }
    }
}