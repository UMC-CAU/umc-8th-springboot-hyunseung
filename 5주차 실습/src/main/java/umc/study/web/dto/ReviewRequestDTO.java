package umc.study.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {
    @Getter
    public static class AddDto {
        @NotNull
        @ExistStore
        Long storeId;
        @NotNull
        @Min(1)
        @Max(5)
        Integer rating;
        @NotBlank
        String text;
        @NotNull
        @ExistMember
        Long memberId;
    }
}