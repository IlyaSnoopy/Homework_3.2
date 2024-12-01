package attachment

data class AudioAttachment(
    val audio: Audio = Audio()
): Attachment {
    override val type: String = "audio"
}