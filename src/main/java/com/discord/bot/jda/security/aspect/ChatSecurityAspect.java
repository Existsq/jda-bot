package com.discord.bot.jda.security.aspect;

import com.discord.bot.jda.security.filter.SecurityFilter;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class ChatSecurityAspect {

  private final List<SecurityFilter> filters;

  @Before("@annotation(com.discord.bot.jda.security.annotation.EnableChatSecurity)")
  public void check(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    GenericEvent event =
        Arrays.stream(args)
            .filter(arg -> arg instanceof GenericEvent)
            .map(arg -> (GenericEvent) arg)
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("GenericEvent not found in method args"));

    try {
      for (SecurityFilter filter : filters) {
        filter.apply(event);
      }
    } catch (SecurityException ex) {
      if (event instanceof MessageReceivedEvent msgEvent) {
        msgEvent.getMessage().delete().queue();
      }
      throw ex;
    }
  }
}
