package com.code.code_be_sub.entity;

import com.code.code_be_sub.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_book", uniqueConstraints = {
        @UniqueConstraint(
                name="BOOK_UNIQUE",
                columnNames={"book_isbn"}
        )
})
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_description")
    private String description;

    @Column(name = "book_isbn", nullable = false, length = 10)
    private String isbn;

    @Column(name = "book_publication_date")
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Builder
    Book(String title, String description, String isbn, LocalDate publicationDate, Author author) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public void update(String title, String description, String isbn, LocalDate publicationDate, Author author) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.author = author;
    }
}
