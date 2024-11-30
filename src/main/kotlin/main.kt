class main {
}

data class Post(
    val id: Long = 0,
    val ownerId: Long = 0,
    val fromId: Long = 0,
    val createdBy: Long = 0,
    val date: Long = 0,
    val text: String = "",
    val replyOwnerId: Long = 0,
    val replyPostId: Long = 0,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: PostType = PostType.POST
)

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

enum class PostType {
    POST, COPY, REPLY, POSTPONE, SUGGEST
}


class WallService {
    private var posts = emptyArray<Post>()
    private var id = 0L

    fun add(post: Post): Post {
        val updated = post.copy(id = ++id)
        posts += updated
        return updated
    }

    fun update(post: Post): Boolean {
        for (i in posts.indices) {
            val current = posts[i]
            if (current.id == post.id) {
                posts[i] = post
                return true
            }
        }
        return false
    }
}
