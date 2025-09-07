package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.common.PointExchange;

public interface PointExchangeRepository extends JpaRepository<PointExchange, Long> {
}