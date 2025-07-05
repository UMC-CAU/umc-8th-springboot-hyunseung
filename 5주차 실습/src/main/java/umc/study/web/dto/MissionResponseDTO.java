package umc.study.web.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.common.Member;
import umc.study.domain.common.Mission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AssignResultDTO{
        Long memberMissionId;
        LocalDateTime createdAt;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddResultDTO{
        Long missionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionStoreListDTO {
        List<MissionResponseDTO.MissionStoreDTO> missionList;
        int listSize;
        int totalPage;
        long totalElements;
        boolean first;
        boolean last;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionStoreDTO {
        private long id;
        private int reward;
        private LocalDate deadline;
        private String missionSpec;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionMemberListDTO {
        List<MissionResponseDTO.MissionMemberDTO> missionList;
        int listSize;
        int totalPage;
        long totalElements;
        boolean first;
        boolean last;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionMemberDTO {
        private long id;
        private long missionId;
        private int reward;
        private LocalDate deadline;
        private String missionSpec;
        private long storeId;
        private String storeName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String status;
    }
}