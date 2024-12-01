object WallService {
    private var posts = emptyArray<Post>()
    private var id = 0L

    fun clear() {
        posts = emptyArray()
        id = 0L
    }

    fun add(post: Post): Post {
        val updated = post.copy(id = ++id, comments = post.comments.copy(), likes = post.likes.copy(), reposts = post.reposts.copy(), views = post.views.copy())
        posts += updated
        return updated
    }

    fun update(post: Post): Boolean {
        for (i in posts.indices) {
            val current = posts[i]
            if (current.id == post.id) {
                posts[i] = post.copy()
                return true
            }
        }
        return false
    }
}