package com.example.SpringPlayground.githubWebhook.model.slack.blocks;

import lombok.Data;


// Text는 단독으로 생성되는것이 아닌 section type안에 감싸져 있어야하므로 block 타입이 아님
@Data
public class Text {

    /**
     * plain_text의 Text
     * @param text = plain_text
     */
    public Text(String text) {
        this.type = "plain_text";
        this.text = text;
    }

    /**
     * mrkdwn type의 Text
     * @param text 글자
     * @param url 클릭했을때 이동하는 url
     */
    public Text(String text,String url) {
        this.type = "mrkdwn";
        this.text = "<" + url + "|" +text + ">";
    }
    private String type;

    private String text;
}
