package com.code.code_be_sub.dto.request;

import com.code.code_be_sub.entity.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAuthorReqDto {
    @NotEmpty
    private String name;

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
