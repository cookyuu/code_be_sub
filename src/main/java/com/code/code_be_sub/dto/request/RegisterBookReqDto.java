package com.code.code_be_sub.dto.request;

import com.code.code_be_sub.entity.Author;
import com.code.code_be_sub.entity.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "도서 등록 요청 DTO")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBookReqDto {

    @Schema(description = "도서 제목", example = "예제 도서")
    @NotEmpty
    private String title;

    @Schema(description = "도서 설명", example = "도서에 대한 설명")
    private String description;

    @Schema(description = "국제 표준 도서번호", example = "예제 도서")
    @NotEmpty
    private String isbn;

    @Schema(description = "출판일", example = "2025-01-01")
    @JsonProperty("publication_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @Schema(description = "해당 도서의 저자", example = "1")
    @NotNull
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
