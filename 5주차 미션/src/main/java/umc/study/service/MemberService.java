package umc.study.service;

import umc.study.domain.common.Member;

public interface MemberService {
    MyPageDTO getMember(long memberId);
    Member saveMember(Member member);
}