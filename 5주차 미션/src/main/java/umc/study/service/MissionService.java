package umc.study.service;

import umc.study.domain.common.Mission;

import java.util.List;

public interface MissionService {
    List<Mission> searchChallengingOrCompleteMissions(long memberId);
}