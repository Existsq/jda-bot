package com.discord.bot.application.useCases

import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.URL

@Component
class DownloadImageByUrlUseCase {

    operator fun invoke(imageUrl: String): ByteArray {
        val url = URL(imageUrl)
        val connection = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connect()
        }

        if (connection.responseCode != HttpURLConnection.HTTP_OK) {
            throw RuntimeException("Failed to download image: HTTP ${connection.responseCode}")
        }

        return connection.inputStream.readBytes()
    }
}