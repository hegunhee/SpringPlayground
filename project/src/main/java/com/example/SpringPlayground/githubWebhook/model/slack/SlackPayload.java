package com.example.SpringPlayground.githubWebhook.model.slack;

import com.example.SpringPlayground.githubWebhook.model.slack.component.SlackPayloadComponent;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class SlackPayload {

    // JsonProperty가 적용되지 않음
    private final List<SlackPayloadComponent> blocks;

    public SlackPayload(List<SlackPayloadComponent> blocks) {
        this.blocks = blocks;
    }

    public List<SlackPayloadComponent> getBlocks() {
        return new ArrayList<>(blocks);
    }
}