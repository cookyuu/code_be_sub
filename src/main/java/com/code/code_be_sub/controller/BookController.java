package com.code.code_be_sub.controller;

import com.code.code_be_sub.dto.request.RegisterBookReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.dto.request.UpdateBookReqDto;
import com.code.code_be_sub.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "도서 관련 API 명세")
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @Operation(summary = "도서 등록", description = "도서를 등록하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register Book Success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Register Book Fail, Bad Request", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Register Book Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity registerBook(@Valid @RequestBody RegisterBookReqDto reqDto) {
        ResponseDto result = bookService.registerBook(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    @Operation(summary = "도서 리스트 조회", description = "도서 리스트를 조회하는 API")
    @Parameter(name = "page", description = "페이지 번호")
    @Parameter(name = "size", description = "한 페이지에 조회할 데이터 수")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Book List Success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Get Book List Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    public ResponseEntity getBookList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publicationDate"));
        ResponseDto result = bookService.getBookList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "도서 상세 조회", description = "도서 상세 정보를 조회하는 API")
    @Parameter(name = "id", description = "도서 Id", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Book Detail Success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Get Book Detail Fail, Book Not Found", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Get Book Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity getBookDetail(@PathVariable(name = "id") Long id) {
        ResponseDto result = bookService.getBookDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "도서 정보 수정", description = "도서의 정보를 수정하는 API")
    @Parameter(name = "id", description = "도서 Id", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update Book Info Success"),
            @ApiResponse(responseCode = "400", description = "Update Book Info Fail, Bad Request", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Update Book Info Fail, Book Not Found", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Update Book Info Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity updateBook(@PathVariable(name = "id") Long id, @Valid @RequestBody UpdateBookReqDto reqDto) {
        ResponseDto result = bookService.updateBook(id, reqDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "도서 정보 삭제", description = "도서를 삭제하는 API (Hard-Delete)")
    @Parameter(name = "id", description = "도서 Id", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete Book Info Success"),
            @ApiResponse(responseCode = "404", description = "Delete Book Info Fail, Book Not Found", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Delete Book Info Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity deleteBook(@PathVariable(name = "id") Long id) {
        ResponseDto result = bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }
}
