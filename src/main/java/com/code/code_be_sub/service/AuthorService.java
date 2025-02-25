package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.RegisterAuthorReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    ResponseDto registerAuthor(RegisterAuthorReqDto reqDto);

    ResponseDto getAuthorDetail(Long id);

    ResponseDto getAuthorList(Pageable pageable);
}
