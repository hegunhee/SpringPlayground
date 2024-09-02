package study.data_jpa.dto;

public class MemberDto {

    private final Long id;
    private final String username;
    private final String teamName;

    public MemberDto(Long id, String username,String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
