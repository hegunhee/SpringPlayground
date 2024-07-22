package com.github.webhook.githubWebhook.service;

import com.github.webhook.githubWebhook.constants.Constants;
import com.github.webhook.model.slack.SlackPayload;
import com.github.webhook.model.slack.component.Section;
import com.github.webhook.model.slack.component.SlackPayloadComponent;
import com.github.webhook.model.slack.component.Text;
import com.github.webhook.model.slack.component.image.Image;
import com.github.webhook.model.slack.component.image.TextImage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SlackMessageTest {
    private final Slack slack = Slack.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();

    private final List<SlackPayloadComponent> components = new ArrayList<>();

    @BeforeEach
    public void clearComponents() {
        components.clear();
    }

    @Test
    public void sendClickableMessage() throws RuntimeException {
        //given
        Text text = Text.createMarkdownText("네이버링크테스트", "https://naver.com");
        Section section = new Section(List.of(text));
        components.add(section);

        //when
        boolean isSuccessful = sendSlackMessage(new SlackPayload(components));

        //then
        assertThat(isSuccessful).isEqualTo(true);
        assertThat(components.size()).isEqualTo(1);
    }

    @Test
    public void sendImageMessage() throws RuntimeException {
        //given
        TextImage textImage = getCatTextImage();
        components.add(textImage);

        //when

        boolean isSuccessful = sendSlackMessage(new SlackPayload(components));
        //then
        assertThat(isSuccessful).isEqualTo(true);
        assertThat(components.size()).isEqualTo(1);
    }

    @Test
    public void hasTwoBlockMessage() throws RuntimeException {
        //given
        Text text = Text.createPlaneText("두개의 블럭이 들어가있는지 테스트");
        Section section = new Section(List.of(text));
        TextImage textImage = getCatTextImage();

        components.add(section);
        components.add(textImage);
        //when
        boolean isSuccessful = sendSlackMessage(new SlackPayload(components));
        //then
        assertThat(isSuccessful).isEqualTo(true);
        assertThat(components.size()).isEqualTo(2);
    }

    @DisplayName("만약 slackWebhookUrl이 다르다면 IOException이 발생")
    @Test
    public void slackUrlIncorrect() {
        String payloadJson;

        Text text = Text.createPlaneText("slackUrl 테스트");
        Section section = new Section(List.of(text));

        components.add(section);
        try {
            payloadJson = payloadObjectToJsonString(new SlackPayload(components));
        } catch (JsonProcessingException jsonProcessingException) {
            log.error("json 파싱중 문제가 발생했습니다. {}", jsonProcessingException.toString());
            throw new RuntimeException(jsonProcessingException);
        }

        Assertions.assertThrows(IOException.class, () ->
                slack.send(Constants.INCORRECT_SLACK_WEBHOOK_URL, payloadJson)
        );
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
        Text githubText = Text.createPlaneText("cute cat");
        return new TextImage(githubText, image);
    }
}