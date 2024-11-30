import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun `add should increment id`() {
        val service = WallService

        val actual = service.add(Post(id = 100_000))

        assertTrue(actual.id != 0L)
    }

    @Test
    fun `update post not exist should returns false`() {
        val service = WallService

        val actual = service.update(Post(id = 100_000))

        assertFalse(actual)
    }

    @Test
    fun `update post exist should returns true`() {
        val service = WallService

        val added = service.add(Post(id = 100_000))
        val actual = service.update(added.copy(text = "test"))

        assertTrue(actual)
    }
}