package com.discord.bot.infrastructure.adapters

import com.discord.bot.application.useCases.DownloadImageByUrlUseCase
import com.discord.bot.domain.command.MessageReceiver
import com.discord.bot.domain.entities.MessageEntity
import com.discord.bot.domain.entities.WelcomeMessageEntity
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.utils.FileUpload
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy

class WelcomeReceiver(
    private val channelId: String
) : MessageReceiver {

    @Autowired
    @Lazy
    lateinit var jda: JDA

    @Autowired
    lateinit var downloadImageByUrlUseCase: DownloadImageByUrlUseCase

    private val log = LoggerFactory.getLogger(WelcomeReceiver::class.java)

    override fun sendMessage(message: MessageEntity) {

        val channel = jda.getTextChannelById(channelId)
        if (channel == null) {
            log.error("Welcome channel not found: $channelId")
            return
        }

        if (message is WelcomeMessageEntity && message.imageUrl != null) {
            try {
                val imageBytes = downloadImageByUrlUseCase(message.imageUrl)
                val fileName = message.imageUrl.substringAfterLast("/")
                val fileUpload = FileUpload.fromData(imageBytes, fileName)
                val embed = EmbedBuilder()
                    .setDescription(message.content)
                    .setImage("attachment://$fileName")
                    .build()
                channel.sendMessageEmbeds(embed)
                    .addFiles(fileUpload)
                    .queue()
            } catch (e: Exception) {
                log.error("Error sending image: ${e.message}")
                channel.sendMessage(message.content).queue()
            }
        } else {
            channel.sendMessage(message.content).queue()
        }
    }
}
