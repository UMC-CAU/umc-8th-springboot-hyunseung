package umc.study.service.memberservice;

import umc.study.domain.common.Member;
import umc.study.web.dto.MemberRequestDTO;

import java.util.List;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
    boolean isFoodCategoryExist(List<Long> values);
    boolean isMemberExist(Long id);
}