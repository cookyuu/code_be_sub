package com.code.code_be_sub.controller;

import com.code.code_be_sub.dto.RegisterBookReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
