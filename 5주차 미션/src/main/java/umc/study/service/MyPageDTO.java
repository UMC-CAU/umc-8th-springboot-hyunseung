package umc.study.service;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import umc.study.domain.common.Member;

@Data
@ToString(callSuper = true)
@Builder
public class MyPageDTO {
    private Member member;
    private int points;
}