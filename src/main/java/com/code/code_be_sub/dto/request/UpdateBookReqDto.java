package com.code.code_be_sub.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookReqDto {
    private String title;
    private String description;
    private String isbn;
    @JsonProperty("publication_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @JsonProperty("author_id")
    private Long authorId;
}
