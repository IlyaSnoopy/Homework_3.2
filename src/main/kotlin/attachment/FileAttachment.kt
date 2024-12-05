package attachment

data class FileAttachment(
    val file: File = File()
) : Attachment {
    override val type: String = "file"
}