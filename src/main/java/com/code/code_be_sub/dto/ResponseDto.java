package com.code.code_be_sub.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL )
public class ResponseDto<T> {
    private String result;
    private String resultMessage;
    private T data;

    public ResponseDto success() {
        return ResponseDto.builder()
                .result("0000")
                .resultMessage("success")
                .build();
    }

    public ResponseDto success(T data) {
        return ResponseDto.builder()
                .result("0000")
                .resultMessage("success")
                .data(data)
                .build();
    }

    public ResponseDto fail(String result, String message) {
        return ResponseDto.builder()
                .result(result)
                .resultMessage(message)
                .build();
    }

    public ResponseDto fail(String result, String message, T data) {
        return ResponseDto.builder()
                .result(result)
                .resultMessage(message)
                .data(data)
                .build();
    }
}
