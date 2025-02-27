package com.code.code_be_sub.controller;

import com.code.code_be_sub.dto.request.RegisterAuthorReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.dto.request.UpdateAuthorReqDto;
import com.code.code_be_sub.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "저자 관련 API 명세")
@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    @Operation(summary = "저자 등록", description = "저자를 등록하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register Author Success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Register Author Fail, Bad Request", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Register Author Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity registerAuthor(@Valid @RequestBody RegisterAuthorReqDto reqDto) {
        ResponseDto result = authorService.registerAuthor(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "저자 상세 조회", description = "저자 상세 정보를 조회하는 API")
    @Parameter(name = "id", description = "저자 Id", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Author Detail Success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Get Author Detail Fail, Author Not Found", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Get Author Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity getAuthorDetail(@PathVariable(name = "id") Long id) {
        ResponseDto result = authorService.getAuthorDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    @Operation(summary = "저자 리스트 조회", description = "저자 리스트를 조회하는 API (삭제된 저자 제외)")
    @Parameter(name = "page", description = "페이지 번호")
    @Parameter(name = "size", description = "한 페이지에 조회할 데이터 수")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Author List Success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Get Author List Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    public ResponseEntity getAuthorList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        ResponseDto result = authorService.getAuthorList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "저자 정보 수정", description = "저자의 정보를 수정하는 API")
    @Parameter(name = "id", description = "저자 Id", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update Author Info Success"),
            @ApiResponse(responseCode = "400", description = "Update Author Info Fail, Bad Request", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Update Author Info Fail, Author Not Found", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Update Author Info Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity updateAuthor(@PathVariable(name = "id") Long id, @Valid @RequestBody UpdateAuthorReqDto reqDto) {
        ResponseDto result = authorService.updateAuthor(id, reqDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result.success());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "저자 정보 삭제", description = "저자의 정보를 삭제하는 API (Soft-Delete)")
    @Parameter(name = "id", description = "저자 Id", example = "1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete Author Info Success"),
            @ApiResponse(responseCode = "404", description = "Delete Author Info Fail, Author Not Found", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Delete Author Info Fail, Internal Server Error", content = @Content(schema = @Schema(implementation = ResponseDto.class))),

    })
    public ResponseEntity deleteAuthor(@PathVariable(name = "id") Long id) {
        ResponseDto result = authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }

}
