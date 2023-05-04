package com.example.groupassessment.request_param.repayment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private Date date;
    private Float amount;
    private Float interestDue;
    private Long LoanId;
    private Long borrowerId;
    private Long paymentMethodId;
}
