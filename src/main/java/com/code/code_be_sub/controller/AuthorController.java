package com.code.code_be_sub.controller;

import com.code.code_be_sub.dto.RegisterAuthorReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity registerAuthor(@RequestBody RegisterAuthorReqDto reqDto) {
        ResponseDto result = authorService.registerAuthor(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAuthorDetail(@PathVariable(name = "id") Long id) {
        ResponseDto result = authorService.getAuthorDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity getAuthorList(@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        ResponseDto result = authorService.getAuthorList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
