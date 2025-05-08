package umc.study.study;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import umc.study.domain.common.*;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.enums.PointExchangeStatus;
import umc.study.domain.mapping.MissionMember;
import umc.study.repository.*;
import umc.study.service.MemberService;
import umc.study.service.MissionService;
import umc.study.service.MyPageDTO;
import umc.study.service.ReviewService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueryDSLTest {
    @Autowired ReviewService reviewService;
    @Autowired MemberService memberService;
    @Autowired MissionService missionService;

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    MissionRepository missionRepository;
    @Autowired
    MissionMemberRepository missionMemberRepository;
    @Autowired
    PointExchangeRepository pointExchangeRepository;

    static Member savedMember;
    static Store savedStore;
    static Mission savedMission;

    @Test
    @Order(1)
    public void testSaveMember() {
        Member member = Member.builder()
                .name("John")
                .sex(Gender.MALE)
                .age(25)
                .birthdate(LocalDate.of(2000, 1, 1))
                .address("Seoul")
                .detailAddress("Gangnam-gu")
                .alive(true)
                .email("john@example.com")
                .phoneNumber("01012345678")
                .build();

        savedMember = memberService.saveMember(member);
        System.out.println(savedMember);
    }

    @Test
    @Order(2)
    public void testSaveStore() {
        Store store = Store.builder()
                .name("BBQ")
                .address("Seoul")
                .detailAddress("Gangnam-daero")
                .food(null)
                .operations(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();

        savedStore = storeRepository.saveAndFlush(store);
        System.out.println(savedStore);
    }

    @Test
    @Order(3)
    public void testReviewServiceSave() {
        Comment comment = reviewService.save(savedMember, savedStore, (byte) 5, "아주 좋아요");
        System.out.println(comment);
    }

    @Test
    @Order(4)
    public void testReviewServiceFindById() {
        Comment saved = reviewService.save(savedMember, savedStore, (byte) 4, "만족합니다");
        Comment found = reviewService.findById(saved.getId());
        System.out.println(found);
    }

    @Test
    @Order(5)
    void testMissionServiceSearchChallengingOrComplete() {
        Member member = memberService.saveMember(
                Member.builder()
                        .name("TestUser")
                        .sex(Gender.MALE)
                        .age(30)
                        .birthdate(LocalDate.of(1995, 1, 1))
                        .address("Seoul")
                        .detailAddress("Test")
                        .alive(true)
                        .email("test@example.com")
                        .phoneNumber("01012341234")
                        .build());

        Store store = storeRepository.saveAndFlush(
                Store.builder()
                        .name("TestStore")
                        .address("Seoul")
                        .detailAddress("Test Street")
                        .operations(List.of())
                        .comments(List.of())
                        .build());

        Mission mission = missionRepository.saveAndFlush(
                Mission.builder()
                        .store(store)
                        .target(10)
                        .reward(100)
                        .build());

        // 이제 외래키 문제 없이 저장 가능
        missionMemberRepository.saveAndFlush(
                MissionMember.builder()
                        .member(member)
                        .mission(mission)
                        .status(MissionStatus.CHALLENGING)
                        .reqTime(LocalDateTime.now())
                        .build());

        List<Mission> result = missionService.searchChallengingOrCompleteMissions(member.getId());
        System.out.println(result);
    }

    @Test
    @Order(6)
    public void testMemberServiceGetPoints() {
        // 미션 완료 및 리워드 저장
        missionMemberRepository.save(MissionMember.builder()
                .mission(savedMission)
                .member(savedMember)
                .status(MissionStatus.COMPLETE)
                .reqTime(LocalDateTime.now().minusDays(1))
                .clearTime(LocalDateTime.now())
                .build());

        // 포인트 사용 기록 추가
        pointExchangeRepository.save(PointExchange.builder()
                .member(savedMember)
                .point(100)
                .reqTime(LocalDateTime.now())
                .status(PointExchangeStatus.CONFIRMED)
                .build());

        MyPageDTO myPage = memberService.getMember(savedMember.getId());
        System.out.println(myPage);
    }
}