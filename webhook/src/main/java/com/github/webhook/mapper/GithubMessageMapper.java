package com.github.webhook.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.webhook.model.github.GithubMessage;
import com.github.webhook.model.github.ping.PingMessage;
import com.github.webhook.model.github.pullrequest.PRMessage;
import com.github.webhook.model.github.push.PushMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GithubMessageMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    private final Map<String, Class<? extends GithubMessage>> messageTypeMap = new HashMap<>();

    public GithubMessageMapper() {
        messageTypeMap.put("ping", PingMessage.class);
        messageTypeMap.put("push", PushMessage.class);
        messageTypeMap.put("pull_request", PRMessage.class);
    }

    /**
     *
     * @param requestBody Github webhook을 통해 전달된 Json 메시지
     * @param eventType ping, push와 같은 요청 타입
     * @return 깃허브 메시지 객체
     */
    public GithubMessage toGithubMessage(String requestBody,String eventType) throws JsonProcessingException {
        return mapper.readValue(requestBody,messageTypeMap.get(eventType));
    }
}