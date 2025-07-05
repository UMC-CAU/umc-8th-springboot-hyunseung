package umc.study.service.storeservice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apipayload.code.status.ErrorStatus;
import umc.study.apipayload.exception.GeneralException;
import umc.study.domain.common.Member;
import umc.study.domain.common.Review;
import umc.study.domain.common.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredStores.forEach(store -> System.out.println("Store: " + store));
        return filteredStores;
    }

    @Override
    public boolean isStoreExist(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    @Override
    public Page<Review> getReviewListByMember(Long memberId, int page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }
}