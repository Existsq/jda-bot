package com.discord.bot.application.command

import com.discord.bot.domain.command.Command
import com.discord.bot.domain.command.MessageReceiver
import com.discord.bot.domain.entities.WelcomeMessageEntity
import lombok.Setter

@Setter
class WelcomeCommand(
    private val receiver: MessageReceiver,
    private var message: WelcomeMessageEntity
) : Command {
    override fun execute() {
        receiver.sendMessage(message)
    }
    //TODO: что то не работает lombok
    fun setMessage(message: WelcomeMessageEntity) {
        this.message = message
    }
}