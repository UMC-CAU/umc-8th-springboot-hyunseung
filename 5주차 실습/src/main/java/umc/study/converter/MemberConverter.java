package umc.study.converter;

import umc.study.domain.common.Member;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
//            case 3 -> Gender.NONE;
            default -> null;
        };

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .email(request.getEmail())
                .password(request.getPassword())   // 추가된 코드
                .age(request.getAge())
                .name(request.getName())
                .role(request.getRole())   // 추가된 코드
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(long id, String accessToken) {
        return MemberResponseDTO.LoginResultDTO.builder()
                .memberId(id)
                .accessToken(accessToken)
                .build();
    }

    public static MemberResponseDTO.MemberInfoDTO toMemberInfoDTO(Member member) {
        return MemberResponseDTO.MemberInfoDTO.builder()
                .email(member.getEmail())
                .name(member.getName())
                .gender(member.getGender().toString())
                .build();
    }
}