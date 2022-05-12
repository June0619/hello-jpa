package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter
@DiscriminatorColumn
public abstract class Item {

    // 조인전략 (설계가 깔끔하므로 거의 정석임)
    // 장점
    // 1. 테이블이 정규화가 되어 있다.
    // 2. 외래키 참조 무결성 제약조건 이용 가능
    // 3. 저장공간 효율화
    // 단점
    // 1. 조인 쿼리가 많이 나간다.
    // 2. 데이터 저장 시 INSERT SQL 이 두번 씩 나간다.

    // 단일테이블 전략
    // 장점
    // 1. 조회 성능이 빠름
    // 2. 쿼리 단순
    // 단점
    // 1. 자식 엔티티가 매핑한 컬럼은 모두 null 을 허용해야 함
    // 2. 테이블이 커질 수 있음

    // 구현 클래스마다 테이블 전략
    // 안쓴다고 생각하면 됨

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
