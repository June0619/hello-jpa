package hellojpa.section2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id
    private Long id;

    private String name;

    @OneToOne(mappedBy = "member")
    private Member member;
}
