package umc.study.domain.common;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.enums.Role;
import umc.study.domain.enums.SocialType;
import umc.study.domain.mapping.MemberAgree;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @ColumnDefault("'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SocialType socialType;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private Role role;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void encodePassword(String password) {
        this.password = password;
    }

//    public void setMemberPreferList(List<FoodCategory> foodCategoryList) {
//        this.memberPreferList.forEach((memberPrefer) -> memberPrefer.setMember(null));
//        this.memberPreferList = foodCategoryList.stream()
//                .map(foodCategory ->
//                        MemberPrefer.builder()
//                                .member(this)
//                                .foodCategory(foodCategory)
//                                .build()
//                ).collect(Collectors.toList());
//    }
}