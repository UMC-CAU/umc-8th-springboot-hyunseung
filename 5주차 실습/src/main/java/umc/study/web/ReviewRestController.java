package umc.study.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apipayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.service.reviewservice.ReviewCommandService;
import umc.study.service.storeservice.StoreQueryService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistStore;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/comments")
    public ApiResponse<ReviewResponseDTO.AddResultDTO> add
            (@RequestBody @Valid ReviewRequestDTO.AddDto request){
        return ApiResponse.onSuccess(ReviewConverter
                .toAddResultDTO(reviewCommandService.addReview(request)));
    }

    @GetMapping("/stores/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다." +
                    " query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",
                    description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003",
                    description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004",
                    description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006",
                    description = "access 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page) { //미션이 아닌 실습 부분임
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO
                (storeQueryService.getReviewList(storeId, page)));
    }

    @GetMapping("/comments/member/{memberId}")
    @Operation(summary = "사용자별 리뷰 목록 조회 API",
            description = "특정 사용자가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다." +
                    " query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",
                    description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003",
                    description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004",
                    description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006",
                    description = "access 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 PK, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewMemberListDTO> getMemberReviewList(
            @NotNull @ExistMember @PathVariable(name = "memberId") Long memberId,
            @NotNull @ValidPage Integer page) { // @RequestParam을 넣으면 작동 안함
        return ApiResponse.onSuccess(ReviewConverter.toReviewMemberListDTO(
                storeQueryService.getReviewListByMember(memberId, page)
        ));
    }
}