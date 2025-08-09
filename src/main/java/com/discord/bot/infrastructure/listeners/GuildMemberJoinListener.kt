package com.discord.bot.infrastructure.listeners

import com.discord.bot.application.command.WelcomeCommand
import com.discord.bot.domain.command.Command
import com.discord.bot.domain.command.Invoker
import com.discord.bot.domain.entities.WelcomeMessageEntity
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class GuildMemberJoinListener(private val commands: List<Command>) : ListenerAdapter(), Invoker {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        // добавляем имя пользователя и фото в entity
        // TODO: сделать динамичным
        commands.forEach { command ->
            if (command is WelcomeCommand) {
                val dynamicMessage = WelcomeMessageEntity(
                    "Welcome to the server, ${event.user.name}!",
                    "https://i.pinimg.com/originals/b5/90/b2/b590b2ed84ff824dd6276fe56b901c13.jpg"
                )
                command.setMessage(dynamicMessage)
            }
        }

        invoke()
    }

    override fun invoke() {
        commands.forEach { it.execute() }
    }
}