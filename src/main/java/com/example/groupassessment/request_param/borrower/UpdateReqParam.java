package com.example.groupassessment.request_param.borrower;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReqParam {
    private String fullName;
    private Integer age;

    private String phone;
}
