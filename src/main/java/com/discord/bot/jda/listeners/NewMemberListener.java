package com.discord.bot.jda.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class NewMemberListener extends ListenerAdapter {

  @Override
  public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {}
}
