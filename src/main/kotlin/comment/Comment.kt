package comment

data class Comment(
    val id: Long = 0,
    val fromId: Long = 0,
    val date: Long = 0,
    var text: String = "",
    var isDeleted: Boolean = false
)
