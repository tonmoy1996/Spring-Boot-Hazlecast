package com.hazelcast.poc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "client")
public class HazelcastClientConfig {
    List<String> members;
}
