package com.discord.bot.web.controller;

import com.discord.bot.web.model.Message;
import com.discord.bot.web.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @PostMapping("/message")
  public ResponseEntity<String> message(@Validated @RequestBody Message message) {
    log.trace("Received message: {}", message);
    messageService.sendMessage(message.profile().name() + ": " + message.message());
    return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
  }
}
