package com.discord.bot.jda.service;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSenderService {

  private final JDA bot;

  public void sendMessage(String message) {
    bot.getTextChannelById(1399803100528906352L).sendMessage(message).queue();
  }
}
