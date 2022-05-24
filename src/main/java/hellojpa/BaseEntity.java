package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
// 1. 직접 인스턴스를 생성할 일이 없으므로 추상클래스로 만드는것을 권장
// 2. 테이블과 관계 없고 공통으로 사용하는 매핑정보를 모으는 역할
public abstract class BaseEntity {
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    public void prePersist() {
        this.createdBy = "SYSTEM";
        this.modifiedBy = "SYSTEM";
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
}
