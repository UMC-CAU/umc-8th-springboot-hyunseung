package umc.study.domain.common;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.enums.Gender;
import umc.study.domain.mapping.MemberFood;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 12)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 6)
    private Gender sex;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, length = 24)
    private String address;

    @Column(nullable = false, length = 128)
    private String detailAddress;

    @Column(name = "is_alive", nullable = false)
    private boolean alive;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, unique = true, length = 12)
    private String phoneNumber;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ident> idents;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> foods;
}