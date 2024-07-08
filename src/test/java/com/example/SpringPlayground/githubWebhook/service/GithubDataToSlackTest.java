package com.example.SpringPlayground.githubWebhook.service;

import com.example.SpringPlayground.githubWebhook.model.github.ping.PingMessage;
import com.example.SpringPlayground.githubWebhook.model.github.pullrequest.PRMessage;
import com.example.SpringPlayground.githubWebhook.model.github.push.PushMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class GithubDataToSlackTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private SlackService slackService;

    @InjectMocks
    private SlackServiceMock slackServiceMock;

    @Test
    public void pushJsonTest() throws IOException {
        ClassPathResource resource = new ClassPathResource("github/push.json");
        PushMessage pushMessage = mapper.readValue(resource.getInputStream(), PushMessage.class);
        log.info("pushMessage = {}",pushMessage.toString());
        assertThat(pushMessage.getUser().getUserName()).isEqualTo("hegunhee");
    }

    @Test
    public void pushSlackPayloadTest() throws IOException {
        //given
        ClassPathResource resource = getResource("github/push.json");
        PushMessage pushMessage = mapper.readValue(resource.getInputStream(), PushMessage.class);

        //when
        SlackPayload slackPayload = pushMessage.toSlackPayload();
        when(slackService.sendSlackNotification(slackPayload)).thenReturn(true);
        boolean isSuccessful = slackServiceMock.sendSlackNotification(slackPayload);

        //then
        assertThat(slackService.sendSlackNotification(slackPayload)).isEqualTo(isSuccessful);
    }

    @Test
    public void pullRequestSlackPayloadTest() throws IOException {
        //given
        ClassPathResource resource = getResource("github/pr_open.json");
        PRMessage prMessage = mapper.readValue(resource.getInputStream(), PRMessage.class);

        //when
        SlackPayload slackPayload = prMessage.toSlackPayload();
        when(slackService.sendSlackNotification(slackPayload)).thenReturn(true);
        boolean isSuccessful = slackServiceMock.sendSlackNotification(slackPayload);

        //then
        assertThat(slackService.sendSlackNotification(slackPayload)).isEqualTo(isSuccessful);
    }

    @Test
    public void pingSlackPayloadTest() throws IOException {
        //given
        ClassPathResource resource = getResource("github/ping.json");
        PingMessage pingMessage = mapper.readValue(resource.getInputStream(), PingMessage.class);

        //when
        SlackPayload slackPayload = pingMessage.toSlackPayload();
        when(slackService.sendSlackNotification(slackPayload)).thenReturn(true);
        boolean isSuccessful = slackServiceMock.sendSlackNotification(slackPayload);

        //then
        assertThat(slackService.sendSlackNotification(slackPayload)).isEqualTo(isSuccessful);
    }

    private ClassPathResource getResource(String path) {
        return new ClassPathResource(path);
    }
}