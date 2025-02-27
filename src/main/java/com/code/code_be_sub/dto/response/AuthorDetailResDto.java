package com.code.code_be_sub.dto.response;

import com.code.code_be_sub.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDetailResDto {
    private Long id;
    private String name;
    private String email;
    private boolean isDeleted;

    public void from(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
        this.isDeleted = author.isDeleted();
    }
}
