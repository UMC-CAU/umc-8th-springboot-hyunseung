package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.common.Member;
import umc.study.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MyPageDTO getMember(long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        return MyPageDTO.builder()
                .member(member)
                .points(memberRepository.getPoints(member))
                .build();
    }

    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.saveAndFlush(member);
    }
}