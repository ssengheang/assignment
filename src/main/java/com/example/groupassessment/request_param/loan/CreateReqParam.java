package com.example.groupassessment.request_param.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private Float loanAmount;
    private int interest;
    private Long borrowerId;
    private Date date;
}
