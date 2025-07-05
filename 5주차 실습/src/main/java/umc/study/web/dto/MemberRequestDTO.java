package umc.study.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto{
        @NotEmpty
        String name;
        @NotEmpty
        String email;
        @NotNull
        Integer gender;
        @NotNull
        Integer age;
        @Size(min = 5, max = 40)
        String address;
        @Size(min = 5, max = 40)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }
}