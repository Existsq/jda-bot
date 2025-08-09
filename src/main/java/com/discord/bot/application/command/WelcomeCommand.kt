package com.discord.bot.application.command

import com.discord.bot.domain.command.Command
import com.discord.bot.domain.command.MessageReceiver
import com.discord.bot.domain.entities.WelcomeMessageEntity
import lombok.Setter
import org.slf4j.LoggerFactory

@Setter
class WelcomeCommand(
    private val receiver: MessageReceiver,
    private var message: WelcomeMessageEntity
) : Command {

    private val log = LoggerFactory.getLogger(WelcomeCommand::class.java)

    override fun execute() {
        log.info("Executing WelcomeCommand with message: $message")
        receiver.sendMessage(message)
    }

    //TODO: что то не работает lombok
    fun setMessage(message: WelcomeMessageEntity) {
        log.info("Setting message in WelcomeCommand: $message")
        this.message = message
    }
}