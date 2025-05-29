package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.common.Mission;
import umc.study.domain.common.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;

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

    public static MissionResponseDTO.MissionStoreListDTO toMissionStoreListDTO(Page<Mission> missionList){
        List<MissionResponseDTO.MissionStoreDTO> missionStoreDTOList = missionList.stream()
                .map(m->MissionResponseDTO.MissionStoreDTO.builder()
                        .id(m.getId())
                        .reward(m.getReward())
                        .deadline(m.getDeadline())
                        .missionSpec(m.getMissionSpec())
                        .createdAt(m.getCreatedAt())
                        .updatedAt(m.getUpdatedAt())
                        .build()).toList();
        return MissionResponseDTO.MissionStoreListDTO.builder()
                .missionList(missionStoreDTOList)
                .last(missionList.isLast())
                .first(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionStoreDTOList.size())
                .build();
    }

    public static MissionResponseDTO.MissionMemberListDTO toMissionMemberListDTO(Page<MemberMission> missionList){
        List<MissionResponseDTO.MissionMemberDTO> missionMemberDTOList = missionList.stream()
                .map(m-> {
                    Mission mission = m.getMission();
                    return MissionResponseDTO.MissionMemberDTO.builder()
                            .id(m.getId())
                            .missionId(mission.getId())
                            .reward(mission.getReward())
                            .deadline(mission.getDeadline())
                            .missionSpec(mission.getMissionSpec())
                            .storeId(mission.getStore().getId())
                            .storeName(mission.getStore().getName())
                            .createdAt(mission.getCreatedAt())
                            .updatedAt(mission.getUpdatedAt())
                            .status(m.getStatus().toString())
                            .build();
                }).toList();
        return MissionResponseDTO.MissionMemberListDTO.builder()
                .missionList(missionMemberDTOList)
                .last(missionList.isLast())
                .first(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionMemberDTOList.size())
                .build();
    }
}