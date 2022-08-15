package tech.stephenlowery

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.webhook

const val TELEGRAM_TOKEN_VAR = "TELEGRAM_TOKEN"
const val HEROKU_APP_NAME_VAR = "HEROKU_APP_NAME"
const val PORT = "PORT"

val bodyweightWorkouts = listOf(
    BodyweightWorkout("jumping jacks", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("sit ups", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("crunches", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("push ups", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("mountain climbers", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("Russian twists", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("plank", RepUnit.SECOND, 20..50, true),
    BodyweightWorkout("leg lifts", RepUnit.SECOND, 20..50),
    BodyweightWorkout("squats", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("lunges", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("door frame rows", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("wall sit", RepUnit.SECOND, 20..50, true),
    BodyweightWorkout("burpees", RepUnit.SELF_NAMED, 20..50),
    BodyweightWorkout("bear crawl", RepUnit.STEP, 20..50),
    BodyweightWorkout("lizard crawl", RepUnit.STEP, 20..50),
)

fun main(args: Array<String>) {
    val herokuAppName = System.getenv(HEROKU_APP_NAME_VAR)
    val bot = bot {
        token = System.getenv(TELEGRAM_TOKEN_VAR)
        webhook {
            val port = System.getenv(PORT) ?: "5000"
            url = "https://${herokuAppName}.herokuapp.com"
            ipAddress = "0.0.0.0:$port"

        }
        dispatch {
            command("workout") {
                val randomWorkout = bodyweightWorkouts.random()
                val reply = "You must $randomWorkout"
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = reply,
                    replyToMessageId = message.messageId,
                )
            }
        }
    }
    bot.startWebhook()
}