package com.code.code_be_sub.service;

import com.code.code_be_sub.domain.Author;
import com.code.code_be_sub.dto.RegisterAuthorReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.global.code.ResultCode;
import com.code.code_be_sub.repository.AuthorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public ResponseDto registerAuthor(@Valid @RequestBody RegisterAuthorReqDto reqDto) {
        ResponseDto result = new ResponseDto();
        if (isEmailDuplicated(reqDto.getEmail())) {
            log.error("Register author fail, Email is duplicated.");
            return result.fail(ResultCode.EMAIL_DUPLICATION_ERROR.getCode(), ResultCode.EMAIL_DUPLICATION_ERROR.getMessage());
        }
        Author author = reqDto.toEntity();
        authorRepository.save(author);
        log.error("Register author success.");
        return result.success();
    }

    private boolean isEmailDuplicated(String email) {
        return authorRepository.existsByEmail(email);
    }
}
