package com.example.SpringPlayground.githubWebhook.model.slack;

import com.example.SpringPlayground.githubWebhook.model.slack.blocks.SlackPayloadComponent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SlackPayload {

    public SlackPayload() {
        this.blocks = new ArrayList();
    }

    // JsonProperty가 적용되지 않음
    private List<SlackPayloadComponent> blocks;

    public void addComponent(SlackPayloadComponent slackPayloadComponent) {
        this.blocks.add(slackPayloadComponent);
    }

}
