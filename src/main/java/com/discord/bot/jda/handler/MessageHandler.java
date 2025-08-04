package com.discord.bot.jda.handler;

import com.discord.bot.jda.security.annotation.EnableChatSecurity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

  @EnableChatSecurity
  public void handle(MessageReceivedEvent event) {
    if (!event.getAuthor().isBot()) {
      event
          .getChannel()
          .sendMessage("New message: " + event.getMessage().getContentDisplay())
          .queue();
    }
  }
}
