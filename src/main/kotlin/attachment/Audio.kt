package attachment

data class Audio(
    val id: Long = 0,
    val ownerId: Long = 0,
    val title: String = "",
    val artist: String = "",
    val duration: Long = 0
)