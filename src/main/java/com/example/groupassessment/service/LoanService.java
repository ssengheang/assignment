package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.request_param.loan.CreateReqParam;
import com.example.groupassessment.request_param.loan.UpdateReqParam;
import com.example.groupassessment.response.LoanView;

import java.util.List;

public interface LoanService {
    List<Loan> index();
    Loan show(Long id);
    Loan create(CreateReqParam loan_req);
    Loan update(Long id, UpdateReqParam loan);
    LoanView<Loan> approve(Long id);
    LoanView<Loan> reject(Long id);
    LoanView<Loan> delete(Long id);
}
