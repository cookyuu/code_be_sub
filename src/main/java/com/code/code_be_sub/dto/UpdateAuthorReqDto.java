package com.code.code_be_sub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuthorReqDto {
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;
}
