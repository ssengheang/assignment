package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.projection.LoanProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.loan.CreateReqParam;
import com.example.groupassessment.request_param.loan.UpdateReqParam;

import java.util.List;
import java.util.Map;

public interface LoanService {
    List<LoanProjection> index(Pagination pagination);
    LoanProjection show(Long id);
    Loan create(CreateReqParam loan_req);
    Loan update(Long id, UpdateReqParam loan);
    Map<String, Boolean> approve(Long id);
    Map<String, Boolean> reject(Long id);
    Boolean delete(Long id);
}
