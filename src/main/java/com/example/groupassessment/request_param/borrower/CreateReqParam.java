package com.example.groupassessment.request_param.borrower;

import com.example.groupassessment.enitity.enum_data_type.PidType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private String fullName;
    private Integer age;

    private String phone;
    private String pidNumber;
    private PidType pidType;
}
