package umc.study.converter;

import umc.study.domain.common.Review;
import umc.study.web.dto.ReviewResponseDTO;

public class ReviewConverter {
    public static ReviewResponseDTO.AddResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddResultDTO.builder()
                .commentId(review.getId())
                .build();
    }
}