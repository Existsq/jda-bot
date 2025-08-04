package com.discord.bot.jda.feature.hello;

import net.dv8tion.jda.api.events.GenericEvent;

public interface Setting {

  boolean isOn();

  void execute(GenericEvent event);
}
