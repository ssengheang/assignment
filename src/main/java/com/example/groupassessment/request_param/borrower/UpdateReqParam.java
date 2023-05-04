package com.example.groupassessment.request_param.borrower;

import com.example.groupassessment.request_param.address.ReqParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReqParam {
    private String fullName;
    private Integer age;

    private String phone;
    private List<ReqParam> address;
}
