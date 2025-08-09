package com.discord.bot.jda.configuration.jda;

import java.util.List;

import com.discord.bot.application.command.WelcomeCommand;
import com.discord.bot.domain.command.Command;
import com.discord.bot.domain.entities.WelcomeMessageEntity;
import com.discord.bot.infrastructure.adapters.WelcomeReceiver;
import com.discord.bot.infrastructure.listeners.GuildMemberJoinListener;
import com.discord.bot.jda.configuration.BotProperties;
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


  @Bean
  public WelcomeReceiver welcomeReceiver() {
    return new WelcomeReceiver("1401992915840012350");
  }

  @Bean
  public ListenerAdapter guildMemberJoinListener(List<Command> commands) {
    return new GuildMemberJoinListener(commands);
  }

  @Bean
  public Command welcomeCommand(WelcomeReceiver welcomeReceiver) {
    WelcomeMessageEntity placeholderEntity = new WelcomeMessageEntity("Placeholder welcome message", null);
    return new WelcomeCommand(welcomeReceiver, placeholderEntity);
  }
}



