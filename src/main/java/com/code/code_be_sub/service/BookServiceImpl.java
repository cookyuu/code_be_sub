package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.*;
import com.code.code_be_sub.entity.Author;
import com.code.code_be_sub.entity.Book;
import com.code.code_be_sub.global.code.ResultCode;
import com.code.code_be_sub.repository.AuthorRepository;
import com.code.code_be_sub.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public ResponseDto getBookList(Pageable pageable) {
        ResponseDto result = new ResponseDto();
        BookListResDto resultData = new BookListResDto();
        List<BookListResDto.BookInfo> resultBooks = new ArrayList<>();
        Page<Book> books = bookRepository.findAll(pageable);
        books.getContent().forEach(book ->
                resultBooks.add(BookListResDto.BookInfo.from(book)));
        resultData.setBooks(resultBooks);
        resultData.setPage(BookListResDto.PageInfo.builder()
                .pageNumber(books.getPageable().getPageNumber())
                .count(books.getNumberOfElements())
                .pageSize(books.getPageable().getPageSize())
                .totalPages(books.getTotalPages())
                .totalElements(books.getTotalElements())
                .build());
        log.info("Select book list success.");
        return result.success(resultData);
    }

    @Override
    public ResponseDto getBookDetail(Long id) {
        ResponseDto result = new ResponseDto();
        BookDetailResDto resData = new BookDetailResDto();
        Book book = findBookById(id);
        resData.from(book);
        log.info("[{}] Select book detail success.", id);
        return result.success(resData);
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResultCode.BOOK_NOT_FOUND.getMessage()));
    }

    private Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResultCode.AUTHOR_NOT_FOUND.getMessage()));
    }
}
