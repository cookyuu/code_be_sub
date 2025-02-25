package com.code.code_be_sub.service;

import com.code.code_be_sub.domain.Author;
import com.code.code_be_sub.dto.AuthorDetailResDto;
import com.code.code_be_sub.dto.RegisterAuthorReqDto;
import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.global.code.ResultCode;
import com.code.code_be_sub.global.exception.CodeCustomException;
import com.code.code_be_sub.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
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
            throw new CodeCustomException(ResultCode.EMAIL_DUPLICATION_ERROR);
        }
        Author author = reqDto.toEntity();
        authorRepository.save(author);
        log.error("Register author success.");
        return result.success();
    }

    @Override
    @Transactional
    public ResponseDto getAuthorDetail(Long id) {
        ResponseDto result = new ResponseDto();
        AuthorDetailResDto resData = new AuthorDetailResDto();
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResultCode.AUTHOR_NOT_FOUND.getMessage()));
        resData.from(author);
        return result.success(resData);
    }

    private boolean isEmailDuplicated(String email) {
        return authorRepository.existsByEmail(email);
    }
}
