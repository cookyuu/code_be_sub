package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.RegisterBookReqDto;
import com.code.code_be_sub.dto.ResponseDto;

public interface BookService {
    ResponseDto registerBook(RegisterBookReqDto reqDto);
}
