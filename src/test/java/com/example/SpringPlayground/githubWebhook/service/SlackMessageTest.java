package com.example.SpringPlayground.githubWebhook.service;

import com.example.SpringPlayground.githubWebhook.constants.Constants;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SlackMessageTest {
    private final Slack slack = Slack.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void sendClickableMessage() throws RuntimeException {
        //given
        SlackPayload slackPayload = new SlackPayload();
        Text text = new Text("네이버링크테스트", "https://naver.com");
        Section section = new Section(List.of(text));
        slackPayload.addBlock(section);

        //when
        boolean isSuccessful = sendSlackMessage(slackPayload);

        //then
        assertThat(isSuccessful).isEqualTo(true);

    }

    @Test
    public void sendImageMessage() throws RuntimeException {
        //given
        SlackPayload slackPayload = new SlackPayload();
        TextImage textImage = getCatTextImage();

        slackPayload.addBlock(textImage);
        //when
        boolean isSuccessful = sendSlackMessage(slackPayload);
        //then
        assertThat(isSuccessful).isEqualTo(true);
    }

    @Test
    public void hasTwoBlockMessage() throws RuntimeException {
        //given
        SlackPayload slackPayload = new SlackPayload();

        Text text = new Text("두개의 블럭이 들어가있는지 테스트");
        Section section = new Section(List.of(text));

        TextImage textImage = getCatTextImage();

        slackPayload.addBlock(section);
        slackPayload.addBlock(textImage);
        //when
        boolean isSuccessful = sendSlackMessage(slackPayload);
        //then
        assertThat(isSuccessful).isEqualTo(true);
    }

    @DisplayName("만약 slackWebhookUrl이 다르다면 IOException이 발생")
    @Test
    public void slackUrlIncorrect() {
        SlackPayload slackPayload = new SlackPayload();
        String payloadJson;

        Text text = new Text("slackUrl 테스트");
        Section section = new Section(List.of(text));

        slackPayload.addBlock(section);
        try {
            payloadJson = payloadObjectToJsonString(slackPayload);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("json 파싱중 문제가 발생했습니다. {}", jsonProcessingException.toString());
            throw new RuntimeException(jsonProcessingException);
        }

        Assertions.assertThrows(IOException.class, () -> {
            slack.send(Constants.INCORRECT_SLACK_WEBHOOK_URL, payloadJson);
        });
    }

    private boolean sendSlackMessage(SlackPayload payload) throws RuntimeException {
        try {
            String jsonString = payloadObjectToJsonString(payload);
            WebhookResponse webhookResponse = slack.send(Constants.SLACK_WEBHOOK_URL, jsonString);
            log.debug("response ={}", webhookResponse.toString());
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("json processing exception ={}", jsonProcessingException.toString());
            throw new RuntimeException(jsonProcessingException);
        } catch (IOException ioe) {
            log.error("IOException = {}", ioe.toString());
        }
        return true;
    }

    private String payloadObjectToJsonString(SlackPayload payload) throws JsonProcessingException {
        return mapper.writeValueAsString(payload);
    }

    private TextImage getCatTextImage() {
        Image image = new Image("https://pbs.twimg.com/profile_images/625633822235693056/lNGUneLX_400x400.jpg", "cute cat");
        Text githubText = new Text("cute cat");
        return new TextImage(githubText, image);
    }
}