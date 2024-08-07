package com.github.webhook.model.github;

import com.github.webhook.model.slack.component.Section;
import com.github.webhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.List;

public class Commits {

    private final List<Commit> commits;

    @JsonCreator
    public Commits(List<Commit> commits) {
        this.commits = commits;
    }

    public Section toCommitTextSection() {
        List<Text> texts = commits.stream().map(Commit::toTextComponent).toList();
        return new Section(texts);
    }

    public List<Commit> getCommits() {
        return new ArrayList<>(commits);
    }
}
