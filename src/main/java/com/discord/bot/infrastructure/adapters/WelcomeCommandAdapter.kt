package com.discord.bot.infrastructure.adapters

import com.discord.bot.domain.command.Command
import com.discord.bot.domain.entities.WelcomeMessageEntity
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import net.dv8tion.jda.api.utils.FileUpload
import java.net.HttpURLConnection
import java.net.URL

class WelcomeCommandAdapter(private val channel: TextChannel, private val message: WelcomeMessageEntity) : Command {
    override fun execute() {
        if (message.imageUrl == null) {
            channel.sendMessage(message.content).queue()
        } else {
            try {
                val imageBytes = downloadImage(message.imageUrl)
                val fileName = message.imageUrl.substringAfterLast("/")
                val fileUpload = FileUpload.fromData(imageBytes, fileName)

                val embed = EmbedBuilder()
                    .setDescription(message.content)
                    .setImage("attachment://$fileName")
                    .build()

                // Отправляем сообщение с embed и вложением
                channel.sendMessageEmbeds(embed)
                    .addFiles(fileUpload)
                    .queue()
            } catch (e: Exception) {
                println("Error sending image: ${e.message}")
                channel.sendMessage(message.content).queue()
            }
        }
    }

    //TODO ВРЕМЕННО!
    // для скачивания изображения
    private fun downloadImage(urlString: String): ByteArray {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        if (connection.responseCode != HttpURLConnection.HTTP_OK) {
            throw RuntimeException("Failed to download image: HTTP ${connection.responseCode}")
        }

        return connection.inputStream.readBytes()
    }
}
