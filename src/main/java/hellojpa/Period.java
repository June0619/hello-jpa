package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

// 값 타입을 정의하는 곳에 표시하는 어노테이션
@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Period {

    private LocalDate startDate;
    private LocalDate endDate;
}
