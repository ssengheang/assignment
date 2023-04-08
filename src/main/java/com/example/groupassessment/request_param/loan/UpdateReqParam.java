package com.example.groupassessment.request_param.loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReqParam {
    private Float loanAmount;
    private int interest;
}
