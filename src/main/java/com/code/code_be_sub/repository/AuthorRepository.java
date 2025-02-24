package com.code.code_be_sub.repository;

import com.code.code_be_sub.domain.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByEmail(@Email @NotEmpty String email);
}
