package umc.study.service.reviewservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apipayload.code.status.ErrorStatus;
import umc.study.apipayload.exception.GeneralException;
import umc.study.domain.common.Review;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public Review addReview(ReviewRequestDTO.AddDto request) {
        Review review = Review.builder()
                .body(request.getText())
                .score(request.getRating())
                .store(storeRepository.findById(request.getStoreId()) //List 없음
                        .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND)))
                .build();
        review.setMember(memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND)));
        return reviewRepository.save(review);
    }
}