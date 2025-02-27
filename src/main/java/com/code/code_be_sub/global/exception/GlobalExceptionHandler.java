package com.code.code_be_sub.global.exception;

import com.code.code_be_sub.dto.ResponseDto;
import com.code.code_be_sub.global.enums.ResultCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CodeCustomException.class)
    public ResponseEntity handlerCodeCustomException(CodeCustomException e) {
        ResponseDto response = new ResponseDto();
        ResultCode resultCode = e.getResultCode();
        log.error("[CodeCustomException] {}", resultCode.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.fail(resultCode.getCode(), resultCode.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseDto response = new ResponseDto();
        log.error("[MethodArgumentNotValidException] ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.fail(ResultCode.BAD_REQUEST.getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity handlerEntityNotFoundException(EntityNotFoundException e) {
        ResponseDto response = new ResponseDto();
        log.error("[EntityNotFoundException] ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.fail(ResultCode.NOT_FOUND.getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity hanlderException(Exception e) {
        ResponseDto response = new ResponseDto();
        log.error("[Exception] ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.fail(ResultCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage()));
    }
}
