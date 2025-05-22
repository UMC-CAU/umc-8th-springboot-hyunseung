package umc.study.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apipayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.service.missionservice.MissionCommandService;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

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
}