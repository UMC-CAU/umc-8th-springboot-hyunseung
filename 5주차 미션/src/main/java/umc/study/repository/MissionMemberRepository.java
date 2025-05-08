package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.mapping.MissionMember;

public interface MissionMemberRepository extends JpaRepository<MissionMember, Long> {
}