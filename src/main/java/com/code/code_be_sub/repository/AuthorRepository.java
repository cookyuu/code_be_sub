package com.code.code_be_sub.repository;

import com.code.code_be_sub.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByEmail(String email);

    Page<Author> findAll(Pageable pageable);
}
