package com.discord.bot.jda.listeners;

import com.discord.bot.jda.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageReceiveListener extends ListenerAdapter {

  private final MessageHandler messageHandler;

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    if (event.getAuthor().isBot()) return;

    messageHandler.handle(event);
  }
}
