package attachment

data class PhotoAttachment(
    val photo: Photo = Photo()
): Attachment {
    override val type: String = "photo"
}