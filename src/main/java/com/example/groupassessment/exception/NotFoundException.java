package com.example.groupassessment.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND,value = HttpStatus.NOT_FOUND,reason = "Not Found!")
public class NotFoundException extends RuntimeException {

    private String code;
    private String message;


    public NotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
