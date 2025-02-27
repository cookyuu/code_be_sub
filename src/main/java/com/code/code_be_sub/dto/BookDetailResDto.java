package com.code.code_be_sub.dto;

import com.code.code_be_sub.entity.Author;
import com.code.code_be_sub.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailResDto {
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private LocalDate publicationDate;
    private AuthorInfo author;

    public void from(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.isbn = book.getIsbn();
        this.publicationDate = book.getPublicationDate();
        this.author = AuthorInfo.builder()
                .id(book.getAuthor().getId())
                .name(book.getAuthor().getName())
                .isDeleted(book.getAuthor().isDeleted())
                .build();
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthorInfo {
        private Long id;
        private String name;
        private boolean isDeleted;
    }
}
