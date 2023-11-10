package com.arquisoft.notification.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;


import java.util.Collection;

@Component
public class ConfigurationPrinter {

  private static Logger logger = LoggerFactory.getLogger(ConfigurationPrinter.class);

  @EventListener
  public void printProperties(ContextRefreshedEvent event) {
    logger.info("************************* ACTIVE PROPERTIES *************************");
    ConfigurableEnvironment env = (ConfigurableEnvironment) event.getApplicationContext().getEnvironment();

    env.getPropertySources()
        .stream()
        .filter(ps -> ps instanceof MapPropertySource && ps.getName().contains("application.properties"))
        .map(ps -> ((MapPropertySource) ps).getSource().keySet())
        .flatMap(Collection::stream)
        .distinct()
        .sorted()
        .forEach(key -> logger.info("{}={}", key, env.getProperty(key)));

    logger.info("*********************************************************************");
  }
}
