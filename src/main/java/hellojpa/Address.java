package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    // primitive 타입은 == 동일비교가 가능하지만
    // 값 타입 객체는 동등비교 (equals) 를 적절하게 재정의 해줌으로써 비교해야 함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zip, address.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zip);
    }

    // 해당 주소가 유효한 주소인지 판별하는 메서드를 다음 같이 도메인 지향적으로 만들 수 있음
//    public void isValid() {
//
//    }

}
