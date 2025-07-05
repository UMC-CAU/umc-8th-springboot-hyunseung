package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.common.Review;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;

public class ReviewConverter {
    public static ReviewResponseDTO.AddResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddResultDTO.builder()
                .commentId(review.getId())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).toList();
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static ReviewResponseDTO.ReviewMemberDTO toReviewMemberDTO(Review review){
        return ReviewResponseDTO.ReviewMemberDTO.builder()
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static ReviewResponseDTO.ReviewMemberListDTO toReviewMemberListDTO(Page<Review> reviewList){
        List<ReviewResponseDTO.ReviewMemberDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewMemberDTO).toList();
        return ReviewResponseDTO.ReviewMemberListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}