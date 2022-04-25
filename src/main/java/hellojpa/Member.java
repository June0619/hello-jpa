package hellojpa;

import RoleType.RoleType;

import javax.persistence.*;
import java.util.Date;

//@Table 실제 테이블 명과 다른 경우 매핑
//JPA가 관리하는 객체
@Entity
public class Member {
    @Id
    private Long id;
    @Column(name="name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member() {}

}
