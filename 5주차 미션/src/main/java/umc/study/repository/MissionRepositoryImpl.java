package umc.study.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.common.Mission;
import umc.study.domain.common.QMission;
import umc.study.domain.common.QStore;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.QMissionMember;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryDSL {
    private final JPAQueryFactory queryFactory;
    private final QMission mission = QMission.mission;
    private final QMissionMember missionMember = QMissionMember.missionMember;
    private final QStore store = QStore.store;

    public List<Mission> searchChallengingOrCompleteMissions(long memberId) {
        return queryFactory
                .select(mission)
                .from(missionMember)
                .join(missionMember.mission, mission)
                .where(new BooleanBuilder()
                        .and(missionMember.status.in(MissionStatus.CHALLENGING, MissionStatus.COMPLETE))
                        .and(missionMember.member.id.eq(memberId)))
                .limit(5).offset(0)
                .fetch();
    }

    public List<Mission> searchRecommendedInAddress(long memberId, String address) {
        return queryFactory
                .select(mission)
                .from(missionMember)
                .join(mission)
                .join(store)
                .where(new BooleanBuilder()
                        .and(missionMember.status.eq(MissionStatus.SUGGESTED))
                        .and(missionMember.member.id.eq(memberId))
                        .and(store.address.eq(address)))
                .limit(5).offset(0)
                .fetch();
    }
}