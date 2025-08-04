package com.discord.bot.web.service;

import com.discord.bot.jda.service.MessageSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageSenderService messageSenderService;

  public void sendMessage(String message) {
    messageSenderService.sendMessage(message);
  }
}