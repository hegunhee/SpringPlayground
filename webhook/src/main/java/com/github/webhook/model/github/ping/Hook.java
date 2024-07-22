package com.github.webhook.model.github.ping;

import com.github.webhook.model.slack.component.Section;
import com.github.webhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hook {

    private final int id;

    private final String sendType;

    private final String hookType;

    private final List<String> events;

    @JsonCreator
    public Hook(
            @JsonProperty("id") int id,
            @JsonProperty("name") String sendType,
            @JsonProperty("type") String hookType,
            @JsonProperty("events") List<String> events
    ) {
        this.id = id;
        this.sendType = sendType;
        this.hookType = hookType;
        this.events = events;
    }

    public Section toSection() {
        Text idText = Text.createPlaneText("id: " + id);
        Text sendTypeText = Text.createPlaneText("보낸 클라이언트: " + sendType);
        Text eventText = Text.createPlaneText("처리하는 이벤트: " + events.toString());

        return new Section(List.of(idText,sendTypeText,eventText));
    }

    public List<String> getEvents() {
        return new ArrayList<>(events);
    }
}
