package umc.study.service.memberservice;

import umc.study.domain.common.Member;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.util.List;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
    boolean isFoodCategoryExist(List<Long> values);
    boolean isMemberExist(Long id);
}