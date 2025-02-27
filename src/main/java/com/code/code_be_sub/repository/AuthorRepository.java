package com.code.code_be_sub.repository;

import com.code.code_be_sub.entity.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Page<Author> findByIsDeletedFalse(Pageable pageable);
    boolean existsByEmailAndIdNotAndIsDeletedFalse(String email, Long id);
    boolean existsByEmailAndIsDeletedFalse(String email);
}
