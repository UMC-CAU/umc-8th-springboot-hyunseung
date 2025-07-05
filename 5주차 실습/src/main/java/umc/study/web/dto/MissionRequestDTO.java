package umc.study.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistMission;
import umc.study.validation.annotation.ExistStore;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class AssignDto{
        @NotNull
        @ExistMission
        Long missionId;
        @NotNull
        @ExistMember
        Long memberId;
    }

    @Getter
    public static class AddDto{
        @NotNull
        @Positive
        private Integer reward;
        @NotNull
        @Future
        private LocalDate deadline;
        @NotBlank
        private String missionSpec;
        @NotNull
        @ExistStore
        private Long storeId;
    }
}