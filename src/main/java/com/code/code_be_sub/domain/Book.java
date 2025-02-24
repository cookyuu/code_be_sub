package com.code.code_be_sub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_description")
    private String description;

    @Column(name = "book_isbn")
    private Integer isbn;

    @Column(name = "book_publication_date")
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
