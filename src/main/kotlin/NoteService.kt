import note.Note
import comment.Comment
import kotlin.jvm.Throws

object NoteService {

    private var notes = mutableListOf<Note>()
    private var commentIdCounter = 0L
    private var noteIdCounter = 0L

    fun clear() {
        notes.clear()
        commentIdCounter = 0L
        noteIdCounter = 0L
    }

    fun add(content: String): Note {
        val note = Note(id = noteIdCounter++, content = content)
        notes.add(note)
        return note
    }

    fun edit(noteId: Long, newContent: String) {
        val note = getById(noteId) ?: throw Exception("Заметка с указанным ID не найдена")
        if (note.isDeleted) throw Exception("Заметка с указанным ID удалена")
        note.content = newContent
    }

    fun delete(noteId: Long) {
        val note = getById(noteId) ?: throw Exception("Заметка с указанным ID не найдена")
        if (note.isDeleted) throw Exception("Заметка с указанным ID уже удалена")
        note.isDeleted = true
        while (note.comments.iterator().hasNext()) {
            note.comments.iterator().next().isDeleted = true
        }
    }

    fun restore(noteId: Long) {
        val note = getById(noteId) ?: throw Exception("Заметка с указанным ID не найдена")
        if (!note.isDeleted) throw Exception("Заметка с указанным ID не удалена(не требует восстановления)")
        note.isDeleted = false
    }

    fun createComment(noteId: Long, text: String): Comment {
        val note = getById(noteId) ?: throw Exception("Заметка с указанным ID не найдена")
        if (note.isDeleted) throw Exception("Заметка с указанным ID удалена")
        val comment = Comment(id = commentIdCounter++, text = text)
        note.comments.add(comment)
        return comment
    }

    fun editComment(noteId: Long, commentId: Long, newContent: String) {
        val note = getById(noteId) ?: throw Exception("Заметка с указанным ID не найдена")
        if (note.isDeleted) throw Exception("Заметка с указанным ID удалена")
        val comment = note.comments.get(commentId.toInt()) ?: throw Exception("Комментарий с указанным ID не найден")
        if (comment.isDeleted) throw Exception("Комментарий с указанным ID удалён")
        comment.text = newContent
    }

    fun deleteComment(noteId: Long, commentId: Long) {
        val note = getById(noteId) ?: throw Exception("Заметка с указанным ID не найдена")
        if (note.isDeleted) throw Exception("Заметка с указанным ID удалена")
        val comment = note.comments.get(commentId.toInt()) ?: throw Exception("Комментарий с указанным ID не найден")
        comment.isDeleted = true
    }

    fun restoreComment(noteId: Long, commentId: Long) {
        val note = getById(noteId) ?: throw Exception("Заметка с указанным ID не найдена")
        if (note.isDeleted) throw Exception("Заметка с указанным ID удалена")
        val comment = note.comments.get(commentId.toInt()) ?: throw Exception("Комментарий с указанным ID не найден")
        comment.isDeleted = false
    }

    fun get(): List<Note> {
        val activeNotes = mutableListOf<Note>()
        for (note in notes) {
            if (!note.isDeleted) {
                activeNotes.add(note)
            }
        }
        return activeNotes
    }

    fun getById(noteId: Long): Note? {
        for (note in notes) {
            if (note.id == noteId) {
                if (!note.isDeleted) {
                    return note
                } else {
                    throw Exception("Заметка с указанным ID удалена")
                }
            }
        }
        throw Exception("Заметка с указанным ID не найдена")
    }

    fun getComments(noteId: Long): List<Comment> {
        for (note in notes) {
            if (note.id == noteId) {
                if (!note.isDeleted) {
                    return note.comments
                } else {
                    throw Exception("Заметка с указанным ID удалена")
                }
            }
        }
        throw Exception("Заметка с указанным ID не найдена")
    }
}