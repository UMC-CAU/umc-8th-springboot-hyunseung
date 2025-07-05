package umc.study.service.memberservice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apipayload.code.status.ErrorStatus;
import umc.study.apipayload.exception.handler.MemberHandler;
import umc.study.config.JwtTokenProvider;
import umc.study.converter.MemberConverter;
import umc.study.domain.common.Member;
import umc.study.repository.MemberRepository;
import umc.study.web.dto.MemberResponseDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request){
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(member);
    }
}