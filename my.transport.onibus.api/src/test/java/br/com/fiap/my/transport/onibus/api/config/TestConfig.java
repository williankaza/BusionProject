package br.com.fiap.my.transport.onibus.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

public class TestConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
