package comment

data class CommentReport(
    val ownerId: Long = 0,
    val commentId: Long = 0,
    val reason: Int = 0
)
