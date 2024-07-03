package com.example.SpringPlayground.githubWebhook.service;

import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Service
@Slf4j
public class SlackService {

    @Value("${notification.slack.webhook.url}")
    private String slackWebhookUrl;

    private final Slack slack = Slack.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();

    public boolean sendSlackNotification(SlackPayload payload) {

        try {
            String json = mapper.writeValueAsString(payload);
            WebhookResponse response = slack.send(slackWebhookUrl, json);
            System.out.println(response);
        } catch (JsonProcessingException e) {
            log.error("json 파싱과정중 에러가 발생했습니다. {}",e.toString());
            return false;
        } catch (IOException e) {
            log.error("slack 메시지 발송 중 문제가 발생했습니다. {}", e.toString());
            throw new RuntimeException(e);
        }
        return true;
    }
}