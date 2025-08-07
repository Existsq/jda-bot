package com.discord.bot.infrastructure.listeners

import com.discord.bot.application.useCases.SendWelcomeUseCase
import com.discord.bot.domain.entities.WelcomeMessageEntity
import com.discord.bot.infrastructure.adapters.WelcomeCommandAdapter
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class GuildMemberJoinListener : ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        val welcomeChannel = event.guild.getTextChannelById("1401992915840012350") ?: return

        val baseMessage = WelcomeMessageEntity("Welcome to the server, {user}!")
        val personalizedMessage = baseMessage.personalize(event.user.name).withImage("https://i.pinimg.com/originals/b5/90/b2/b590b2ed84ff824dd6276fe56b901c13.jpg")
        val command = WelcomeCommandAdapter(welcomeChannel, personalizedMessage)
        val useCase = SendWelcomeUseCase(command)

        useCase.invoke()
    }
}