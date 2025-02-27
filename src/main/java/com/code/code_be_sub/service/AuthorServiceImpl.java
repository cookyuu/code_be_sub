package com.code.code_be_sub.service;

import com.code.code_be_sub.dto.*;
import com.code.code_be_sub.dto.request.RegisterAuthorReqDto;
import com.code.code_be_sub.dto.request.UpdateAuthorReqDto;
import com.code.code_be_sub.dto.response.AuthorDetailResDto;
import com.code.code_be_sub.dto.response.AuthorListResDto;
import com.code.code_be_sub.entity.Author;
import com.code.code_be_sub.global.enums.ResultCode;
import com.code.code_be_sub.global.exception.CodeCustomException;
import com.code.code_be_sub.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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
            throw new CodeCustomException(ResultCode.EMAIL_DUPLICATION);
        }
        Author author = reqDto.toEntity();
        authorRepository.save(author);
        log.info("Register author success.");
        return result.success();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto getAuthorDetail(Long id) {
        ResponseDto result = new ResponseDto();
        AuthorDetailResDto resData = new AuthorDetailResDto();
        Author author = findAuthorById(id);
        resData.from(author);
        log.info("[{}] Select author detail success.", id);
        return result.success(resData);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto getAuthorList(Pageable pageable) {
        ResponseDto result = new ResponseDto<>();
        AuthorListResDto resultData = new AuthorListResDto();
        List<AuthorListResDto.AuthorInfo> resultAuthors = new ArrayList<>();
        Page<Author> authors = authorRepository.findByIsDeletedFalse(pageable);
        authors.getContent().forEach(author ->
                resultAuthors.add(AuthorListResDto.AuthorInfo.from(author)));

        resultData.setAuthors(resultAuthors);
        resultData.setPage(AuthorListResDto.PageInfo.builder()
                .pageNumber(authors.getPageable().getPageNumber())
                .count(authors.getNumberOfElements())
                .pageSize(authors.getPageable().getPageSize())
                .totalPages(authors.getTotalPages())
                .totalElements(authors.getTotalElements())
                .build());
        log.info("Select author list success.");
        return result.success(resultData);
    }

    @Override
    @Transactional
    public ResponseDto updateAuthor(Long id, UpdateAuthorReqDto reqDto) {
        ResponseDto result = new ResponseDto();
        Author author = findAuthorById(id);
        if (author.isDeleted()) {
            throw new CodeCustomException(ResultCode.AUTHOR_DELETED);
        }
        if (authorRepository.existsByEmailAndIdNotAndIsDeletedFalse(reqDto.getEmail(), id)) {
            throw new CodeCustomException(ResultCode.EMAIL_DUPLICATION);
        }
        author.update(reqDto.getName(), reqDto.getEmail());
        return result.success();
    }

    @Override
    @Transactional
    public ResponseDto deleteAuthor(Long id) {
        ResponseDto result = new ResponseDto();
        Author author = findAuthorById(id);
        if (author.isDeleted()) {
            throw new CodeCustomException(ResultCode.AUTHOR_DELETED);
        }
        author.delete();
        return result.success();
    }

    private boolean isEmailDuplicated(String email) {
        return authorRepository.existsByEmailAndIsDeletedFalse(email);
    }

    private Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ResultCode.AUTHOR_NOT_FOUND.getMessage()));
    }
}
