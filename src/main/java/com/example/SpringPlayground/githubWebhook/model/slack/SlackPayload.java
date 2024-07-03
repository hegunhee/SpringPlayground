package com.example.SpringPlayground.githubWebhook.model.slack;

import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Block;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SlackPayload {

    public SlackPayload() {
        this.blocks = new ArrayList();
    }
    private List<Block> blocks;

    public void addBlock(Block block) {
        this.blocks.add(block);
    }

}
