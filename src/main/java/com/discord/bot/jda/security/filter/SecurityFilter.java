package com.discord.bot.jda.security.filter;

import net.dv8tion.jda.api.events.GenericEvent;

public interface SecurityFilter {
  void apply(GenericEvent event) throws SecurityException;
}
