package umc.study.service.missionservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apipayload.code.status.ErrorStatus;
import umc.study.apipayload.exception.GeneralException;
import umc.study.domain.common.Member;
import umc.study.domain.common.Mission;
import umc.study.domain.common.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public Mission addMission(MissionRequestDTO.AddDto request) {
        return missionRepository.save(Mission.builder()
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .store(storeRepository.findById(request.getStoreId()) //List 없음
                        .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND)))
                .build());
    }

    @Transactional
    @Override
    public MemberMission assignMission(MissionRequestDTO.AssignDto missionId) {
        MemberMission memberMission = MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
        memberMission.setMember(memberRepository.findById(missionId.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND)));
        memberMission.setMission(missionRepository.findById(missionId.getMissionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND)));
        return memberMissionRepository.save(memberMission);
    }

    @Override
    public boolean isMissionExist(Long id) {
        return missionRepository.existsById(id);
    }

    @Override
    public Page<Mission> getMissionByStore(Long storeId, int page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        return missionRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    @Override
    public Page<MemberMission> getMissionByMemberChallenging(Long memberId, int page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        return memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING,
                PageRequest.of(page, 10));
    }
}