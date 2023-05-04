package com.example.groupassessment.request_param.bank_account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private String keyId;
    private String accountName;
    private Long borrowerId;
    private Long bankId;
}
