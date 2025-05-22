package umc.study.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apipayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.service.reviewservice.ReviewCommandService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.AddResultDTO> add
            (@RequestBody @Valid ReviewRequestDTO.AddDto request){
        return ApiResponse.onSuccess(ReviewConverter
                .toAddResultDTO(reviewCommandService.addReview(request)));
    }
}