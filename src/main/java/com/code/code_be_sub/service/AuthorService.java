package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.RegisterAuthorReqDto;
import com.code.code_be_sub.dto.ResponseDto;

public interface AuthorService {
    ResponseDto registerAuthor(RegisterAuthorReqDto reqDto);
}
