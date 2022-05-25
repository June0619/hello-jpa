package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * 임베디드 타입의 장점
 * 1. 재사용 용이
 * 2. 높은 응집도
 * 3. 객체지향적인 메서드 작성 가능
 * 4. 엔티티에 생명주기 의존
 * **/

@Embeddable
@Getter
//불변객체로 설계하기 위해 Setter를 제거한다.
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zip;

    // 해당 주소가 유효한 주소인지 판별하는 메서드를 다음 같이 도메인 지향적으로 만들 수 있음
//    public void isValid() {
//
//    }

}
