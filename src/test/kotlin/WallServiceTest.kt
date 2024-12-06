import attachment.Photo
import attachment.PhotoAttachment
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import post.Post

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addShouldIncrementId() {
        val service = WallService

        val actual = service.add(
            Post(
                id = 100_000,
                attachments = listOf(PhotoAttachment(Photo(ownerId = 1_200)))
            )
        )

        assertTrue(actual.id != 0L)
    }

    @Test
    fun updatePostNotExistShouldReturnsFalse() {
        val service = WallService

        val actual = service.update(Post(id = 100_000))

        assertFalse(actual)
    }

    @Test
    fun updatePostExistShouldReturnsTrue() {
        val service = WallService

        val added = service.add(Post(id = 100_000))
        val actual = service.update(added.copy(text = "test"))

        assertTrue(actual)
    }

    @Test
    fun shouldThrow() {
        val comment = Comment(id = 1, text = "Комментарии к несуществующему посту")
        assertThrows(PostNotFoundException::class.java) {
            WallService.createComment(postId = 1, comment)
        }
    }

    @Test
    fun shouldAddCommentToExistingPost() {
        val post = Post(id = 1, text = "Тестовый пост")
        WallService.add(post)

        val comment = Comment(text = "Тестовый комментарий")
        val result = WallService.createComment(1, comment)

        val comments = WallService.getComments()
        assertEquals(comment.text, result.text)
    }

    @Test
    fun reportValidComment() {
        val post = Post(id = 1, text = "Тестовый пост")
        WallService.add(post)

        val comment = Comment(text = "Тестовый комментарий")
        val addedComment = WallService.createComment(1, comment)

        val report = WallService.reportComment(addedComment.fromId, addedComment.id, 3)

        assertTrue(report)
    }

    @Test
    fun throwExceptionWhenInvalidCommentId() {
        val post = Post(id = 1, text = "Тестовый пост")
        WallService.add(post)

        val comment = Comment(text = "Тестовый комментарий")
        val addedComment = WallService.createComment(1, comment)

        assertThrows(IllegalArgumentException::class.java) {
            WallService.reportComment(addedComment.fromId, commentId = 100, reason = 3)
        }
    }

    @Test
    fun throwExceptionWhenInvalidReason() {
        val post = Post(id = 1, text = "Тестовый пост")
        WallService.add(post)

        val comment = Comment(text = "Тестовый комментарий")
        val addedComment = WallService.createComment(1, comment)

        assertThrows(IllegalStateException::class.java) {
            WallService.reportComment(addedComment.fromId, addedComment.id, reason = 9)
        }
    }
}


