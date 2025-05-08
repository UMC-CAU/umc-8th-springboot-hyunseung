package umc.study.repository;

import umc.study.domain.common.Mission;

import java.util.List;

public interface MissionRepositoryDSL {
    List<Mission> searchChallengingOrCompleteMissions(long memberId);
}