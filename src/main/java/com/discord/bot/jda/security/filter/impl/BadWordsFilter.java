package com.discord.bot.jda.security.filter.impl;

import com.discord.bot.jda.security.filter.SecurityFilter;
import java.util.Set;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class BadWordsFilter implements SecurityFilter {

  private static final Set<String> BAD_WORDS = Set.of("nigger");

  public void apply(GenericEvent event) {
    if (event instanceof MessageReceivedEvent e) {
      String[] words = e.getMessage().getContentRaw().toLowerCase().split("\\s+");
      for (String word : words) {
        if (BAD_WORDS.contains(word)) {
          throw new SecurityException("Запрещённое слово");
        }
      }
    }
  }
}
