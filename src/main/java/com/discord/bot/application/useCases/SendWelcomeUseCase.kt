package com.discord.bot.application.useCases

import com.discord.bot.domain.command.Command

class SendWelcomeUseCase(private val command: Command) {
    fun invoke() {
        command.execute()
    }
}