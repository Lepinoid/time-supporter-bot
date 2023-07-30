import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class TestTimeMessage {
    @Test
    fun testLocalDateTime() {
        val time = Instant.fromEpochMilliseconds(0)
        val utc = getLocalTime("UTC", time)
        assertEquals("0:00", utc)
        val cet = getLocalTime("CET", time)
        assertEquals("1:00", cet)
        val jst = getLocalTime("Asia/Tokyo", time)
        assertEquals("9:00", jst)
    }
}