package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.common.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}