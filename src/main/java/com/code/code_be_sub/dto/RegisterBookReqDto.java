package com.code.code_be_sub.dto;

import com.code.code_be_sub.entity.Author;
import com.code.code_be_sub.entity.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBookReqDto {
    private String title;
    private String description;
    private String isbn;
    @JsonProperty("publication_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @JsonProperty("author_id")
    private Long authorId;

    public Book toEntity(Author author) {
        return Book.builder()
                .title(this.title)
                .description(this.title)
                .isbn(this.isbn)
                .publicationDate(this.publicationDate)
                .author(author)
                .build();
    }
}
