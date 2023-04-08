package com.example.groupassessment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerView<T> {
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BorrowerView(String code, String message){
        this.code = code;
        this.message = message;
    }
}
