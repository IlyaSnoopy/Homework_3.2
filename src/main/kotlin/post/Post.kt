package post

import attachment.Attachment

data class Post(
    val id: Long = 0,
    val ownerId: Long = 0,
    val fromId: Long = 0,
    val createdBy: Long = 0,
    val date: Long = 0,
    val text: String = "",
    val replyOwnerId: Long? = null,
    val replyPostId: Long? = null,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: PostType = PostType.POST,
    val attachments: List<Attachment> = emptyList()
)