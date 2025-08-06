package com.discord.bot.jda.configuration;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("bot")
public class BotProperties {

  private String token;

  private List<GatewayIntent> intents;

  public EnumSet<GatewayIntent> getIntents() {
    if (intents == null || intents.isEmpty()) {
      return EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
    }
    return EnumSet.copyOf(intents.stream().filter(Objects::nonNull).collect(Collectors.toSet()));
  }
}
