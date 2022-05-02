package hellojpa.section2;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @ToString
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="MEMBER_NAME")
    private String memberName;

//    @Column(name="TEAM_ID")
//    private Long teamId;

    //연관관계의 주인 - 바퀴 (wheel)
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    //연관관계 편의 메서드
    //관례상 setter 를 사용하기보다 별도의 이름을 부여하는 것이 좋음
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    @Builder
    public Member(String name, Team team) {
        this.memberName = name;
        this.team = team;
        team.getMembers().add(this);
    }

}
