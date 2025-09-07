package umc.study.repository;

import umc.study.domain.common.Member;

public interface MemberRepositoryDSL {
    public int getPoints(Member member);
}