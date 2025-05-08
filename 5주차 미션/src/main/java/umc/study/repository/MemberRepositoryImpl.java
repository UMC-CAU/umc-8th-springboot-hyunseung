package umc.study.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.common.Member;
import umc.study.domain.common.QMember;
import umc.study.domain.common.QMission;
import umc.study.domain.common.QPointExchange;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.enums.PointExchangeStatus;
import umc.study.domain.mapping.QMissionMember;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl {
    private final JPAQueryFactory queryFactory;
    private final QMember qmember = QMember.member;
    private final QMissionMember missionMember = QMissionMember.missionMember;
    private final QMission mission = QMission.mission;
    private final QPointExchange pointExchange = QPointExchange.pointExchange;

    public int getPoints(Member member) {
        return Objects.requireNonNullElse(queryFactory
                .select(mission.reward.sum())
                .from(missionMember)
                .join(missionMember.member, qmember)
                .where(new BooleanBuilder()
                        .and(missionMember.member.eq(member))
                        .and(missionMember.status.eq(MissionStatus.COMPLETE)))
                .fetchOne(), 0) -
                Objects.requireNonNullElse(queryFactory
                        .select(pointExchange.point.sum())
                        .from(pointExchange)
                        .where(new BooleanBuilder()
                                .and(pointExchange.member.eq(member))
                                .and(pointExchange.status.in
                                        (PointExchangeStatus.CONFIRMED,
                                                PointExchangeStatus.COMPLETED)))
                        .fetchOne(), 0);
    }
}