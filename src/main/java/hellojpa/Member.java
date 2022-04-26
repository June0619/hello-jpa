package hellojpa;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@Table 실제 테이블 명과 다른 경우 매핑
//JPA가 관리하는 객체
@Entity
@Data
//@SequenceGenerator(name = "member_sequence", sequenceName = "member_seq")
public class Member {
    //Id 만 있을 경우 기본키를 직접 입력
    @Id
    // AUTO 는 DB 방언에 따라 자동 생성
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Long id;
    //nullable -> Not null 제약조건
    //unique
    @Column(name="name", insertable = true, updatable = true, nullable = false)
    private String name;
    private Integer age;

    //EnumType.ORDINAL 사용하면 운영 시 큰 문제가 될 수 있음
    @Enumerated(EnumType.ORDINAL)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //하이버네이트 최신 버전에서는 자동 매핑 된다.
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    //많은 문자열 컬럼
    @Lob
    private String description;

    //DB와 관계 없는 컬럼
    @Transient
    private String temp;

    public Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
