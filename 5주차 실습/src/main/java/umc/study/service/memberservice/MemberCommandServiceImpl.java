package umc.study.service.memberservice;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apipayload.code.status.ErrorStatus;
import umc.study.apipayload.exception.handler.FoodCategoryHandler;
import umc.study.apipayload.exception.handler.MemberHandler;
import umc.study.config.JwtTokenProvider;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.domain.common.FoodCategory;
import umc.study.domain.common.Member;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberRepository;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAllById(request.getPreferCategory());
        if (foodCategoryList.size() != request.getPreferCategory().size())
            throw new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND);
//                request.getPreferCategory().stream()
//                .map(category -> {
//                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
//                }).collect(Collectors.toList());
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));
        return memberRepository.save(newMember);
    }

    @Override
    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return MemberConverter.toLoginResultDTO(
                member.getId(),
                accessToken
        );
    }


    @Override
    @Transactional(readOnly = true)
    public boolean isFoodCategoryExist(List<Long> values) {
        return values.size() == foodCategoryRepository.findAllById(values).size();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isMemberExist(Long id) {
        return memberRepository.existsById(id);
    }
}