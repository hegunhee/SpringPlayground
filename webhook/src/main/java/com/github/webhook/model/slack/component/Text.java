package com.github.webhook.model.slack.component;

import lombok.*;


// Text는 단독으로 생성되는것이 아닌 section type안에 감싸져 있어야하므로 block 타입이 아님
@Getter
public class Text {

    private final String type;
    private final String text;

    private Text(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public static Text createPlaneText(String text) {
        String type = "plain_text";
        return new Text(type,text);
    }

    public static Text createMarkdownText(String text,String url) {
        String type = "mrkdwn";
        String SlackMarkdownString = "<" + url + "|" +text + ">";
        return new Text(type,SlackMarkdownString);
    }
}
