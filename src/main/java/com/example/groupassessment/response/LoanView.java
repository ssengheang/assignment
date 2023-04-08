package com.example.groupassessment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanView<T> {
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public LoanView(String code, String message){
        this.code = code;
        this.message = message;
    }
}
