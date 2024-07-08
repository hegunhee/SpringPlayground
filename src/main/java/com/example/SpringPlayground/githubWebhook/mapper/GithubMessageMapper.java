package com.example.SpringPlayground.githubWebhook.mapper;

import com.example.SpringPlayground.githubWebhook.model.github.GithubMessage;
import com.example.SpringPlayground.githubWebhook.model.github.ping.PingMessage;
import com.example.SpringPlayground.githubWebhook.model.github.pullrequest.PRMessage;
import com.example.SpringPlayground.githubWebhook.model.github.push.PushMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GithubMessageMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    private final Map<String, Class<? extends GithubMessage>> messageTypeMap = new HashMap();

    public GithubMessageMapper() {
        messageTypeMap.put("ping", PingMessage.class);
        messageTypeMap.put("push", PushMessage.class);
        messageTypeMap.put("pull_request", PRMessage.class);
    }

    /**
     *
     * @param requestBody 요청값
     * @param eventType ping, push와 같은 요청 타입
     * @return 깃허브 메시지
     */
    public GithubMessage toGithubMessage(String requestBody,String eventType) throws JsonProcessingException {
        return mapper.readValue(requestBody,messageTypeMap.get(eventType));
    }
}
