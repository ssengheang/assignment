package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Repayment;
import com.example.groupassessment.enitity.projection.RepaymentProjection;
import com.example.groupassessment.request_param.repayment.CreateReqParam;

import java.util.List;

public interface RepaymentService {
    List<RepaymentProjection> index();
    RepaymentProjection show(Long id);
    Repayment create(CreateReqParam repayment);
    Boolean delete(Long id);
}
