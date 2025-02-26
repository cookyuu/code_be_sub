package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.RegisterBookReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.entity.Author;
import com.code.code_be_sub.entity.Book;
import com.code.code_be_sub.global.code.ResultCode;
import com.code.code_be_sub.repository.AuthorRepository;
import com.code.code_be_sub.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public ResponseDto registerBook(RegisterBookReqDto reqDto) {
        ResponseDto result = new ResponseDto();
        Author author = findAuthorById(reqDto.getAuthorId());
        // isbn (국제 표준 도서번호 - 고유)
        // 추후 isbn 검증 필요 - 형식, 중복 유무
        Book book = reqDto.toEntity(author);
        bookRepository.save(book);
        log.info("Register book success.");
        return result.success();
    }

    private Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResultCode.AUTHOR_NOT_FOUND.getMessage()));
    }
}
