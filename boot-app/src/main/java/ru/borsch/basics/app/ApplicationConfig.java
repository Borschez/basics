package ru.borsch.basics.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {ru.borsch.basics.config.JpaConfiguration.class})
public class ApplicationConfig {
}
