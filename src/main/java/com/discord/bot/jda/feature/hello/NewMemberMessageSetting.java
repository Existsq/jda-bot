package com.discord.bot.jda.feature.hello;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NewMemberMessageSetting implements Setting {

  @Override
  public boolean isOn() {
    return true;
  }

  @Override
  public void execute(GenericEvent event) {
    if (event instanceof GuildMemberJoinEvent e) {
      e.getGuild()
          .getTextChannelById(1401992915840012350L)
          .sendMessage("Welcome " + e.getMember().getEffectiveName())
          .queue();
    }
  }
}
