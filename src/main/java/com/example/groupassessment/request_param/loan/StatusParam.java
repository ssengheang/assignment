package com.example.groupassessment.request_param.loan;
import com.example.groupassessment.enitity.enum_data_type.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusParam {
    private LoanStatus status;
}
