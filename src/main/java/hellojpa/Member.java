package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    //Period
    // 값 타입을 사용하는 곳에 표시하는 어노테이션
    @Embedded
    private Period period;

    //Address
    // 값 타입을 사용하는 곳에 표시하는 어노테이션
    @Embedded
    private Address address;


}
