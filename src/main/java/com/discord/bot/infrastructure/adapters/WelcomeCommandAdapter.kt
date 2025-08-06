package com.discord.bot.infrastructure.adapters

import com.discord.bot.domain.command.Command
import com.discord.bot.domain.entities.WelcomeMessageEntity
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel

class WelcomeCommandAdapter(private val channel: TextChannel, private val message: WelcomeMessageEntity) : Command {
    override fun execute() {
        channel.sendMessage(message.content).queue()
    }
}