package com.discord.bot.domain.entities

data class WelcomeMessageEntity(
    val content: String,
    val imageUrl: String? = null
) {
    init {
        require(content.isNotBlank()) { "Welcome message cannot be blank" }

        if (imageUrl != null) {
            require(imageUrl.isNotBlank()) { "Invalid image URL" }
        }
    }

    // для кастомизации
    fun personalize(userName: String): WelcomeMessageEntity {
        return copy(content = content.replace("{user}", userName))
    }

    fun withImage(url: String): WelcomeMessageEntity {
        return copy(imageUrl = url)
    }
}