package com.example.SpringPlayground.githubWebhook.service;

import com.example.SpringPlayground.githubWebhook.model.github.ping.PingMessage;
import com.example.SpringPlayground.githubWebhook.model.github.pullrequest.PRMessage;
import com.example.SpringPlayground.githubWebhook.model.github.push.PushMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

@Slf4j
@SpringBootTest
@TestPropertySource("classpath:application-test.yml")
public class GithubDataToSlackTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private final SlackService slackService;

    @Autowired
    public GithubDataToSlackTest(SlackService slackService) {
        this.slackService = slackService;
    }

    @Test
    public void pushJsonTest() throws IOException {
        ClassPathResource resource = new ClassPathResource("github/push.json");
        PushMessage pushMessage = mapper.readValue(resource.getInputStream(), PushMessage.class);
        log.info("pushMessage = {}",pushMessage.toString());
        Assertions.assertThat(pushMessage.getUser().getUserName().equals("hegunhee"));
    }

    @Test
    public void pushSlackPayloadTest() throws IOException {
        ClassPathResource resource = getResource("github/push.json");
        PushMessage pushMessage = mapper.readValue(resource.getInputStream(), PushMessage.class);
        SlackPayload slackPayload = pushMessage.toSlackPayload();
        slackService.sendSlackNotification(slackPayload);
    }

    @Test
    public void pullRequestSlackPayloadTest() throws IOException {
        ClassPathResource resource = getResource("github/pr_open.json");
        PRMessage prMessage = mapper.readValue(resource.getInputStream(), PRMessage.class);
        SlackPayload slackPayload = prMessage.toSlackPayload();
        slackService.sendSlackNotification(slackPayload);
    }

    @Test
    public void pingSlackPayloadTest() throws IOException {
        ClassPathResource resource = getResource("github/ping.json");
        PingMessage pingMessage = mapper.readValue(resource.getInputStream(), PingMessage.class);
        SlackPayload slackPayload = pingMessage.toSlackPayload();
        slackService.sendSlackNotification(slackPayload);
    }

    private ClassPathResource getResource(String path) {
        return new ClassPathResource(path);
    }
}