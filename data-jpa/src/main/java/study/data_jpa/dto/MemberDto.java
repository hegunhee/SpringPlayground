package study.data_jpa.dto;

import study.data_jpa.entity.Member;

public class MemberDto {

    private final Long id;
    private final String username;
    private final String teamName;

    public MemberDto(Long id, String username,String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public MemberDto(Member member) {
        id = member.getId();
        username = member.getUsername();
        teamName = member.getTeam().getName();
    }
}
