package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.common.Member;
import umc.study.domain.common.Review;
import umc.study.domain.common.Store;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    @EntityGraph(attributePaths = {"store"})
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}