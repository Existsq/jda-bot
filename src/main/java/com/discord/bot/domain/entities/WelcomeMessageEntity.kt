package com.discord.bot.domain.entities

data class WelcomeMessageEntity(
    val content: String
) {
    init {
        require(content.isNotBlank()) { "Welcome message cannot be blank" }
    }

    // для кастомизации
    fun personalize(userName: String): WelcomeMessageEntity {
        return copy(content = content.replace("{user}", userName))
    }
}