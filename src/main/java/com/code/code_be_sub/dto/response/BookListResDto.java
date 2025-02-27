package com.code.code_be_sub.dto.response;

import com.code.code_be_sub.entity.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookListResDto {
    private List<BookInfo> books;
    private PageInfo page;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookInfo {
        private Long id;
        private String title;
        private String description;
        private String isbn;
        private LocalDate publicationDate;
        private AuthorInfo author;

        public static BookInfo from(Book book) {
            return BookInfo.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .isbn(book.getIsbn())
                    .publicationDate(book.getPublicationDate())
                    .author(AuthorInfo.builder()
                            .id(book.getAuthor().getId())
                            .name(book.getAuthor().getName())
                            .isDeleted(book.getAuthor().isDeleted())
                            .build())
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
