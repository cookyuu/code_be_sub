package com.code.code_be_sub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "저자 정보 수정 요청 DTO")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuthorReqDto {
    @Schema(description = "저자 이름", example = "홍길동수정")
    @NotEmpty
    private String name;

    @Schema(description = "저자 이메일", example = "hong99@naver.com")
    @Email
    @NotEmpty
    private String email;
}
