package note
import comment.Comment

data class Note(
    val id: Long,
    var content: String,
    val comments: MutableList<Comment> = mutableListOf(),
    var isDeleted: Boolean = false
)