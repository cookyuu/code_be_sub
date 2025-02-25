package com.code.code_be_sub.dto;

import com.code.code_be_sub.domain.Author;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorListResDto {
    private List<AuthorInfo> authors;
    private PageInfo page;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthorInfo {
        private String name;
        private String email;

        public static AuthorInfo from(Author author) {
            return AuthorInfo.builder()
                    .name(author.getName())
                    .email(author.getEmail())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageInfo {
        private int pageNumber;
        private int count;
        private int pageSize;
        private int totalPages;
        private long totalElements;
    }
}
