package com.discord.bot.jda.configuration;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BotConfig {

  private final BotProperties botProperties;

  @Bean
  public JDA botInstance(List<ListenerAdapter> listeners) throws InterruptedException {
    JDABuilder builder =
        JDABuilder.createLight(botProperties.getToken(), botProperties.getIntents());

    listeners.forEach(builder::addEventListeners);

    return builder.build().awaitReady();
  }
}
