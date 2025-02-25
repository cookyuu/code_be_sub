package com.code.code_be_sub.global.exception;

import com.code.code_be_sub.global.code.ResultCode;
import lombok.Getter;

@Getter
public class CodeCustomException extends RuntimeException {
    private final ResultCode resultCode;
    private String message;
    private String[] args;
    private Object data;

    public CodeCustomException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    protected CodeCustomException(ResultCode resultCode, String message) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.message = message;
    }

    protected CodeCustomException(ResultCode resultCode, Object data, String[] args) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.data = data;
        this.args = args;
    }

    protected CodeCustomException(ResultCode resultCode, Throwable t) {
        super(t);
        this.resultCode = resultCode;
    }

    protected CodeCustomException(ResultCode resultCode, Object data) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.data = data;
    }

}
