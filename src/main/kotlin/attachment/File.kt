package attachment

data class File(
    val id: Long = 0,
    val ownerId: Long = 0,
    val title: String = "",
    val size: Long = 0,
    val ext: String = "",
    val url: String = ""
)