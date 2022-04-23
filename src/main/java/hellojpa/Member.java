package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Table 실제 테이블 명과 다른 경우 매핑
//JPA가 관리하는 객체
@Entity
public class Member {
    @Id
    private Long id;
    private String name;

    public Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
