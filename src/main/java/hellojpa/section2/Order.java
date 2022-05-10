package hellojpa.section2;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Order {

    //MEMBER_ID 와 PRODUCT_ID 를 묶어 PK를 만드는 것 보다
    //새롭게 PK를 생성하는 것이 애플리케이션 설계 측면에서 훨씬 유연하다.
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    /**
     * N:M 관계는 다음과 같이 중계 테이블을 엔티티로 승격시켜 풀어낸다.
     * **/

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private int price;

    private LocalDateTime orderedAt;
}
