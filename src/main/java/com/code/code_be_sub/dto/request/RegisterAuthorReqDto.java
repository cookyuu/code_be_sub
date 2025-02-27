package com.code.code_be_sub.dto.request;

import com.code.code_be_sub.entity.Author;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "저자 등록 요청 DTO")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAuthorReqDto {

    @Schema(description = "저자 이름", example = "홍길동")
    @NotEmpty
    private String name;

    @Schema(description = "저자 이메일", example = "hong9@naver.com")
    @Email
    @NotEmpty
    private String email;

    public Author toEntity() {
        return Author.builder()
                .name(name)
                .email(email)
                .build();
    }
}
