import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

val TIMEZONE_REGEX = Regex("(\\d{1,2}):(\\d{2}) (.{2,3})")

fun getTimeMessage(message: String): LocalTime? {
    if (message.contains(TIMEZONE_REGEX)) {
        val match = TIMEZONE_REGEX.find(message)
        val (hour, minute, timezone) = match!!.destructured
        return try {
            TODO()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    return null
}

fun getLocalTime(timezone: String, instant: Instant = Clock.System.now()): String {
    return try {
        val time = instant.toLocalDateTime(TimeZone.of(timezone))
        "${time.hour}:${time.minute.toString().padStart(2, '0')}"
    } catch (e: Exception) {
        e.printStackTrace()
        "00:00"
    }
}