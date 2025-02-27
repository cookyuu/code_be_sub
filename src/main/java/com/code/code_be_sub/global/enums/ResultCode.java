package com.code.code_be_sub.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {
    SUCCESS("0000", "SUCCESS."),
    BAD_REQUEST("9001", "BAD_REQUEST"),
    NOT_FOUND("9002", "NOT_FOUND"),
    INTERNAL_SERVER_ERROR("9003", "INTERNAL_SERVER_ERROR"),

    EMAIL_DUPLICATION("1000", "이미 등록된 이메일입니다."),
    AUTHOR_NOT_FOUND("1001", "등록되지 않은 저자입니다."),
    AUTHOR_DELETED("1002", "삭제된 저자입니다."),

    BOOK_NOT_FOUND("2000", "등록되지 않은 도서입니다.");

    private String code;
    private String message;

}
