package com.example.groupassessment.enitity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@ToString
public class ApiResponse<T> {
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagination pagination;

    public ApiResponse() {
        this.code = "OK";
        this.message = "OK";
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(T data) {
        this.code = "OK";
        this.message = "OK";
        this.data = data;
    }

    public ApiResponse(T data, Pagination pagination) {
        this.code = "OK";
        this.message = "OK";
        this.data = data;
        this.pagination = pagination;
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

