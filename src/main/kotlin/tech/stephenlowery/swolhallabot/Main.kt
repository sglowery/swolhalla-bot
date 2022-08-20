package tech.stephenlowery.swolhallabot

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId

const val TELEGRAM_TOKEN_VAR = "TELEGRAM_TOKEN"

fun main(args: Array<String>) {
    val bot = bot {
        token = System.getenv(TELEGRAM_TOKEN_VAR)
        dispatch {
            command("workout") {
                val randomWorkout = message.from?.id?.let { WorkoutHandler.getWorkoutForUser(it) } ?: return@command
                val reply = "You must $randomWorkout"
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = reply,
                    replyToMessageId = message.messageId,
                )
            }
        }
    }
    bot.startPolling()
}