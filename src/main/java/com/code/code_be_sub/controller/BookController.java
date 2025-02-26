package com.code.code_be_sub.controller;

import com.code.code_be_sub.dto.RegisterBookReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity registerBook(@RequestBody RegisterBookReqDto reqDto) {
        ResponseDto result = bookService.registerBook(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity getBookList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        ResponseDto result = bookService.getBookList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookDetail(@PathVariable(name = "id") Long id) {
        ResponseDto result = bookService.getBookDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
