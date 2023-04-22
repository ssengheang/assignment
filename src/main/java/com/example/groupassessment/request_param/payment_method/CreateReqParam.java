package com.example.groupassessment.request_param.payment_method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private String methodName;
    private Long BankId;
}
