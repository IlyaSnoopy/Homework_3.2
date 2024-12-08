import comment.Comment
import comment.CommentReport
import post.Post
import post.PostNotFoundException

object WallService {
    private var posts = emptyArray<Post>()
    private var id = 0L

    fun clear() {
        posts = emptyArray()
        comments = emptyArray()
        reportedComments = emptyArray()
        id = 0L
    }

    fun add(post: Post): Post {
        val updated = post.copy(
            id = ++id,
            comments = post.comments.copy(),
            likes = post.likes.copy(),
            reposts = post.reposts.copy(),
            views = post.views.copy()
        )
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

    private var comments = emptyArray<Comment>()
    private var nextCommentId = 1L

    // Метод для создания комментария
    fun createComment(postId: Long, comment: Comment): Comment {
        var postExists = false
        for (post in posts) {
            if (post.id == postId) {
                postExists = true
                break
            }
        }
        if (postExists) {
            val newComment = comment.copy(id = nextCommentId++)
            comments += newComment
            return newComment
        } else {
            throw PostNotFoundException("Post with ID $postId not found.")
        }
    }

    fun getComments(): Array<Comment> {
        return comments
    }

    private var reportedComments = emptyArray<CommentReport>()

    fun reportComment(ownerId: Long, commentId: Long, reason: Int): Boolean {
        if (reason !in 0..8) {
            throw IllegalStateException("Неверная причина жалобы: $reason")
        }

        var existingComment = false
        for (comment in comments) {
            if (comment.id == commentId) {
                existingComment = true
                break
            }
        }

        val reportedComment = CommentReport(ownerId, commentId, reason)

        if (existingComment) {
            reportedComments += reportedComment
            return true
        }
        throw IllegalArgumentException("Комментарий с таким ID не найден")
    }
}