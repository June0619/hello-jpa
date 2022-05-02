package hellojpa.section2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    //가짜 연관관계 - 차 (car)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

}
