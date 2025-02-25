package com.code.code_be_sub.dto;

import com.code.code_be_sub.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDetailResDto {
    private String name;
    private String email;

    public void from(Author author) {
        this.name = author.getName();
        this.email = author.getEmail();
    }
}
