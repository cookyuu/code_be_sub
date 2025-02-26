package com.code.code_be_sub.repository;

import com.code.code_be_sub.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
