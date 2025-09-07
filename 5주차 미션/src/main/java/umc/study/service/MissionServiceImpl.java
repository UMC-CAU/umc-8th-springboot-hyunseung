package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.common.Mission;
import umc.study.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;
    public List<Mission> searchChallengingOrCompleteMissions(long memberId) {
        List<Mission> missions = missionRepository.searchChallengingOrCompleteMissions(memberId);
        missions.forEach(System.out::println);
        return missions;
    }
}