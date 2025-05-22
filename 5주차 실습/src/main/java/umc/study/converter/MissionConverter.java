package umc.study.converter;

import umc.study.domain.common.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionResponseDTO;

public class MissionConverter {
    public static MissionResponseDTO.AssignResultDTO toAssignResultDTO(MemberMission mission) {
        return MissionResponseDTO.AssignResultDTO.builder()
                .memberMissionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }
    public static MissionResponseDTO.AddResultDTO toAddResultDTO(Mission mission) {
        return MissionResponseDTO.AddResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}