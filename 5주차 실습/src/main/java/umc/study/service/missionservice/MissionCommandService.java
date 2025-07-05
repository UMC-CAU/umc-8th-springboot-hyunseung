package umc.study.service.missionservice;

import umc.study.domain.common.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMission(MissionRequestDTO.AddDto request);
    MemberMission assignMission(MissionRequestDTO.AssignDto missionId);
    boolean isMissionExist(Long id);
}