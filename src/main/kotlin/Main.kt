import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.edit
import dev.kord.core.entity.channel.VoiceChannel
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.minutes

const val BOT_TOKEN = "BOT_TOKEN"
const val JP_CHANNEL = "JP_CHANNEL"
const val CET_CHANNEL = "CET_CHANNEL"

suspend fun main() {
    val env = System.getenv()
    check(env.containsKey(BOT_TOKEN)) { "env BOT_TOKEN is null" }
    check(env.containsKey(JP_CHANNEL)) { "env JP_CHANNEL is null" }
    check(env.containsKey(CET_CHANNEL)) { "env CET_CHANNEL is null" }

    val kord = Kord(System.getenv(BOT_TOKEN))
//    kord.on<MessageCreateEvent> {
//        if (message.author?.isBot == true) return@on
//    }
    kord.on<ReadyEvent> {
        kord.launch {
            while (true) {
                kord.getChannelOf<VoiceChannel>(Snowflake(env[CET_CHANNEL]!!))?.edit {
                    name = "🇪🇺 ${getLocalTime("CET")}"
                } ?: run {
                    println("CET_CHANNEL is null")
                }
                kord.getChannelOf<VoiceChannel>(Snowflake(env[JP_CHANNEL]!!))?.edit {
                    name = "🇯🇵 ${getLocalTime("Asia/Tokyo")}"
                } ?: run {
                    println("JP_CHANNEL is null")
                }
                delay(15.minutes)
            }
        }
    }
    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}