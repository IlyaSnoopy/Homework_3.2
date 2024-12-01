package attachment

data class UrlAttachment(
    val url: Url = Url()
): Attachment {
    override val type: String = "url"
}