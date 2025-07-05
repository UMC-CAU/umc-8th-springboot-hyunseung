package umc.study.service.reviewservice;

import umc.study.domain.common.Review;
import umc.study.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review addReview(ReviewRequestDTO.AddDto request);
}