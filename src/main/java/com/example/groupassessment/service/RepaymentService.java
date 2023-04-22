package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Repayment;
import com.example.groupassessment.request_param.repayment.CreateReqParam;

import java.util.List;

public interface RepaymentService {
    List<Repayment> index();
    Repayment show(Long id);
    Repayment create(CreateReqParam repayment);
    String delete(Long id);
}
