package hellojpa.section2;

import hellojpa.section1.RoleType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity @Getter @Setter
public class Member {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="MEMBER_NAME")
    private String memberName;

    @Column(name="TEAM_ID")
    private Long teamId;

}