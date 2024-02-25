package com.hazelcast.poc.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class HazelcastConfig {

    @Value("${client.members: 127.0.0.1:5701}")
    List<String> members;

    @Bean
    public ClientConfig config() {
        ClientConfig clientConfig= new ClientConfig();
        clientConfig.setClusterName("dev-local");
        clientConfig.setInstanceName("myInstance");

        System.out.println(members);
        clientConfig.getNetworkConfig()
                .setConnectionTimeout(5000)
                .addAddress(String.join(" ", members));

        return clientConfig;

    }
}
