package com.example.SpringPlayground.githubWebhook.model.github.ping;

import com.example.SpringPlayground.githubWebhook.model.slack.component.Section;
import com.example.SpringPlayground.githubWebhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hook {

    private int id;

    @JsonProperty("name")
    private String sendType;

    @JsonProperty("type")
    private String hookType;

    private List<String> events;

    public Section toSection() {
        Text idText = new Text("id: " + id);
        Text sendTypeText = new Text("보낸 클라이언트: " + sendType);
        Text eventText = new Text("처리하는 이벤트: " + events.toString());

        return new Section(List.of(idText,sendTypeText,eventText));
    }


}
