package umc.study.service.missionservice;

import org.springframework.data.domain.Page;
import umc.study.domain.common.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMission(MissionRequestDTO.AddDto request);
    MemberMission assignMission(MissionRequestDTO.AssignDto missionId);
    boolean isMissionExist(Long id);

    Page<Mission> getMissionByStore(Long storeId, int page);

    Page<MemberMission> getMissionByMemberChallenging(Long memberId, int page);
}