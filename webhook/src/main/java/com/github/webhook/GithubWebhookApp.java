package com.github.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GithubWebhookApp {

    public static void main(String[] args) {
        SpringApplication.run(GithubWebhookApp.class, args);
    }
}
