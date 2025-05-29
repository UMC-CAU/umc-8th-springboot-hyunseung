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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apipayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.common.Mission;
import umc.study.service.missionservice.MissionCommandService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    @PostMapping("/assign")
    public ApiResponse<MissionResponseDTO.AssignResultDTO> assign
            (@RequestBody @Valid MissionRequestDTO.AssignDto request){
        return ApiResponse.onSuccess(MissionConverter
                .toAssignResultDTO(missionCommandService.assignMission(request)));
    }
    @PostMapping("/add")
    public ApiResponse<MissionResponseDTO.AddResultDTO> add
            (@RequestBody @Valid MissionRequestDTO.AddDto request){
        return ApiResponse.onSuccess(MissionConverter
                .toAddResultDTO(missionCommandService.addMission(request)));
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "가게별 미션 목록 조회 API",
            description = "가게별로 할당된 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다." +
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
            @Parameter(name = "storeId", description = "가게 PK, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionStoreListDTO> getMissionListByStore(
            @NotNull @ExistStore @PathVariable(name = "storeId") Long storeId,
            @NotNull @Positive @RequestParam(name = "page") Integer page) {
        return ApiResponse.onSuccess(MissionConverter.toMissionStoreListDTO(
                missionCommandService.getMissionByStore(storeId, page - 1)));
    }

    @GetMapping("/member/challenging")
    @Operation(summary = "도전 중인 미션 목록 조회 API",
            description = "사용자가 도전중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다." +
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
    public ApiResponse<MissionResponseDTO.MissionMemberListDTO> getMissionListChallengingByMember(
            @NotNull @Positive @RequestParam(name = "page") Integer page,
            @NotNull @ExistMember @RequestParam(name = "memberId") Long memberId) {
        //member는 Auth로 받아와야 하는데...
        return ApiResponse.onSuccess(MissionConverter.toMissionMemberListDTO(
                missionCommandService.getMissionByMemberChallenging(memberId, page - 1)
        ));
    }
}