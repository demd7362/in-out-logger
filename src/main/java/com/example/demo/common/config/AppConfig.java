package com.example.demo.common.config;


import com.example.demo.model.SecretConfig;
import com.example.demo.tool.SystemEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Configuration
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppConfig {
    private final ObjectMapper objectMapper;
    @Bean
    public SecretConfig secretConfig() throws IOException {
        Path path;
        if (SystemEnvironment.isWindows()) {
            path = Path.of("/config.json");
        } else if (SystemEnvironment.isLinux()) {
            path = Path.of("etc/config.json");
        } else {
            throw new RuntimeException("Unsupported OS");
        }
        String json = Files.readString(path);
        SecretConfig secretConfig = objectMapper.readValue(json, SecretConfig.class);
        return secretConfig;
    }
}
