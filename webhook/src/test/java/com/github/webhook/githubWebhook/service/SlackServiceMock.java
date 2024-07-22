package com.github.webhook.githubWebhook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.webhook.model.slack.SlackPayload;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.github.webhook.githubWebhook.constants.Constants.SLACK_WEBHOOK_URL;

@Slf4j
public class SlackServiceMock {

    private final Slack slack = Slack.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();

    public boolean sendSlackNotification(SlackPayload payload) throws RuntimeException {
        try {
            String payloadJson = mapper.writeValueAsString(payload);
            WebhookResponse webhookResponse = slack.send(SLACK_WEBHOOK_URL, payloadJson);

            log.debug("payloadJson ={}",payloadJson);
            String webhookMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(webhookResponse);
            log.debug("webhookMessage ={}",webhookMessage);
        } catch (JsonProcessingException e) {
            log.error("slack 메시지 json 직렬화도중 에러가 발생했습니다. {}",e.toString());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("slack 메시지 발송 중 문제가 발생했습니다. {}", e.toString());
            throw new RuntimeException(e);
        }
        return true;
    }
}
