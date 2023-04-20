package com.example.groupassessment.request_param.asset;

import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private String name;
    private Float estimateValue;
    private Long typeId;
    private Long loanId;

}
