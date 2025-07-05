package umc.study.service.storeservice;

import org.springframework.data.domain.Page;
import umc.study.domain.common.Review;
import umc.study.domain.common.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    boolean isStoreExist(Long id);
    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Review> getReviewListByMember(Long memberId, int page);
}