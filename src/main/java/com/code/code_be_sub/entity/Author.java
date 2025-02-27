package com.code.code_be_sub.entity;

import com.code.code_be_sub.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_author")
public class Author extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "author_name", nullable = false)
    private String name;

    @Column(name = "author_email", nullable = false)
    private String email;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    @Builder
    Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void delete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
