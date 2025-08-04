package com.discord.bot.jda.feature.hello;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WelcomeFeature extends ListenerAdapter {

  private final List<Setting> settings;

  @Override
  public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
    for (Setting setting : settings) {
      if (setting.isOn()) {
        setting.execute(event);
      }
    }
  }
}
