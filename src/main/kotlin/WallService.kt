object WallService {
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