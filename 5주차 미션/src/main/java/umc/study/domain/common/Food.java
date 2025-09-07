package umc.study.domain.common;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Food extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, length = 12)
    private String name;
}