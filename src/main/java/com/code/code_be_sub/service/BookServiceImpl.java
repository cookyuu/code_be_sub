package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.*;
import com.code.code_be_sub.dto.request.RegisterBookReqDto;
import com.code.code_be_sub.dto.request.UpdateBookReqDto;
import com.code.code_be_sub.dto.response.BookDetailResDto;
import com.code.code_be_sub.dto.response.BookListResDto;
import com.code.code_be_sub.entity.Author;
import com.code.code_be_sub.entity.Book;
import com.code.code_be_sub.global.enums.ResultCode;
import com.code.code_be_sub.global.exception.CodeCustomException;
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
        String reqIsbn = reqDto.getIsbn();
        validateIsbn(reqIsbn);
        if (bookRepository.existsByIsbn(reqIsbn)) {
            throw new CodeCustomException(ResultCode.BOOK_ISBN_DUPLICATION);
        }
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
    @Transactional(readOnly = true)
    public ResponseDto getBookDetail(Long id) {
        ResponseDto result = new ResponseDto();
        BookDetailResDto resData = new BookDetailResDto();
        Book book = findBookById(id);
        resData.from(book);
        log.info("[{}] Select book detail success.", id);
        return result.success(resData);
    }

    @Override
    @Transactional
    public ResponseDto updateBook(Long id, UpdateBookReqDto reqDto) {
        ResponseDto result = new ResponseDto();
        Book book = findBookById(id);
        Author author = book.getAuthor();
        String isbn = book.getIsbn();
        if (!author.getId().equals(reqDto.getAuthorId())) {
            author = findAuthorById(reqDto.getAuthorId());
        }
        if (!isbn.equals(reqDto.getIsbn())) {
            validateIsbn(reqDto.getIsbn());
            if (bookRepository.existsByIsbnAndIdNot(reqDto.getIsbn(), book.getId())) {
                throw new CodeCustomException(ResultCode.BOOK_ISBN_DUPLICATION);
            }
            isbn = reqDto.getIsbn();
        }
        book.update(reqDto.getTitle(), reqDto.getDescription(), isbn, reqDto.getPublicationDate(), author);
        log.info("[{}] Update book success.", id);
        return result.success();
    }

    @Override
    @Transactional
    public ResponseDto deleteBook(Long id) {
        ResponseDto result = new ResponseDto();
        Book book = findBookById(id);
        bookRepository.delete(book);
        return result.success();
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResultCode.BOOK_NOT_FOUND.getMessage()));
    }

    private Author findAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResultCode.AUTHOR_NOT_FOUND.getMessage()));
        if (author.isDeleted()) {
            throw new CodeCustomException(ResultCode.AUTHOR_DELETED);
        }
        return author;
    }

    private void validateIsbn(String isbn) {
        String[] isbnSplit = isbn.split("");
        String localeCode = isbnSplit[0].concat(isbnSplit[1]);
        String publisherCode = isbnSplit[2].concat(isbnSplit[3]).concat(isbnSplit[4]).concat(isbnSplit[5]);
        String bookCode = isbnSplit[6].concat(isbnSplit[7]).concat(isbnSplit[8]);
        String endCode = isbnSplit[9];

        if (isbnSplit.length != 10) {
            throw new CodeCustomException(ResultCode.BOOK_ISBN_VALIDATION);
        }
        if (10 > Integer.valueOf(localeCode) || Integer.valueOf(localeCode) > 90) {
            throw new CodeCustomException(ResultCode.BOOK_ISBN_VALIDATION);
        }
        if (!endCode.equals("0")) {
            throw new CodeCustomException(ResultCode.BOOK_ISBN_VALIDATION);
        }
    }

}
