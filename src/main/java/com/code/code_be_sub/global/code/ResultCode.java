package com.code.code_be_sub.global.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {
    SUCCESS("0000", "SUCCESS."),
    EMAIL_DUPLICATION_ERROR("1000", "이미 등록된 이메일입니다.");

    private String code;
    private String message;

}
