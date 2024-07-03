package com.example.SpringPlayground.githubWebhook.service;

import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Section;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Text;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.image.Image;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.image.TextImage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
@TestPropertySource("classpath:application-test.yml")
class SlackServiceTest {

    @Value("${notification.slack.webhook.url}")
    private String slackWebhookUrl;

    private final Slack slack = Slack.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void sendClickableMessage() throws RuntimeException {
        SlackPayload slackPayload = new SlackPayload();
        Text text = new Text("네이버링크테스트", "https://naver.com");
        Section section = new Section(List.of(text));
        slackPayload.addBlock(section);
        try {
            String json = payloadObjectToJson(slackPayload);
            log.info("json = {}",json);
            WebhookResponse response = slack.send(slackWebhookUrl, json);
            log.info("응답값 = {}",response);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("json 파싱중 문제가 발생했습니다. {}",jsonProcessingException.toString());
            throw new RuntimeException(jsonProcessingException);
        } catch (IOException e) {
            log.error("slack 메시지 발송 중 문제가 발생했습니다. {}", e.toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sendImageMessage() {
        SlackPayload slackPayload = new SlackPayload();
        TextImage textImage = getCatTextImage();

        slackPayload.addBlock(textImage);
        try {
            String json = payloadObjectToJson(slackPayload);
            log.info("json = {}",json);
            WebhookResponse send = slack.send(slackWebhookUrl, json);
            log.info("응답값 = {}",send);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("json 파싱중 문제가 발생했습니다. {}",jsonProcessingException.toString());
            throw new RuntimeException(jsonProcessingException);
        } catch (IOException e) {
            log.error("slack 메시지 발송 중 문제가 발생했습니다. {}", e.toString());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void hasTwoBlockMessage() {
        SlackPayload slackPayload = new SlackPayload();

        Text text = new Text("두개의 블럭이 들어가있는지 테스트");
        Section section = new Section(List.of(text));

        TextImage textImage = getCatTextImage();

        slackPayload.addBlock(section);
        slackPayload.addBlock(textImage);
        try {
            String json = mapper.writeValueAsString(slackPayload);
            log.info("json = {}",json);
            WebhookResponse send = slack.send(slackWebhookUrl, json);
            log.info("응답값 = {}",send);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("json 파싱중 문제가 발생했습니다. {}",jsonProcessingException.toString());
            throw new RuntimeException(jsonProcessingException);
        } catch (IOException e) {
            log.error("slack 메시지 발송 중 문제가 발생했습니다. {}", e.toString());
            throw new RuntimeException(e);
        }
    }

    private String payloadObjectToJson(SlackPayload payload) throws JsonProcessingException {
        return mapper.writeValueAsString(payload);
    }

    private TextImage getCatTextImage() {
        Image image = new Image("https://pbs.twimg.com/profile_images/625633822235693056/lNGUneLX_400x400.jpg", "cute cat");
        Text githubText = new Text("cute cat");
        return new TextImage(githubText, image);
    }
}