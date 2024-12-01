package attachment

data class VideoAttachment(
    val video: Video = Video()
): Attachment {
    override val type: String = "video"
}