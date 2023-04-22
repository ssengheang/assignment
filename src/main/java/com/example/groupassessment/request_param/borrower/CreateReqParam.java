package com.example.groupassessment.request_param.borrower;

import com.example.groupassessment.enitity.enum_data_type.PidType;
import com.example.groupassessment.request_param.address.ReqParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private String fullName;
    private Integer age;

    private String phone;
    private String pidNumber;
    private PidType pidType;

    private List<ReqParam> address;
}
