package umc.study.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
public abstract class BaseEntity {
    @CreatedDate
    @Column(nullable = true, updatable = false, scale = 6)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = true, updatable = true, scale = 6)
    private LocalDateTime updatedAt;
}