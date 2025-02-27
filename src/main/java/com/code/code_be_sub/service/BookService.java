package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.request.RegisterBookReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.dto.request.UpdateBookReqDto;
import org.springframework.data.domain.Pageable;

public interface BookService {

    ResponseDto registerBook(RegisterBookReqDto reqDto);

    ResponseDto getBookList(Pageable pageable);

    ResponseDto getBookDetail(Long id);

    ResponseDto updateBook(Long id, UpdateBookReqDto reqDto);

    ResponseDto deleteBook(Long id);
}
