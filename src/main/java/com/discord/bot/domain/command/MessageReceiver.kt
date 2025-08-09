package com.discord.bot.domain.command

import com.discord.bot.domain.entities.MessageEntity

// Получатель сообщений
interface MessageReceiver {
    fun sendMessage(message: MessageEntity)
}
