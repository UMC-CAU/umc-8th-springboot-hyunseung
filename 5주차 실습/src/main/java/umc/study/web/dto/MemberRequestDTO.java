package umc.study.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto{
        @NotEmpty
        String name;
        @NotBlank
        @Email
        String email;    // 이메일 필드 추가
        @NotBlank
        String password;    // 비밀번호 필드 추가
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
        @NotNull
        Role role;    // 역할 필드 추가
    }
}