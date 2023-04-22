package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.request_param.borrower.CreateReqParam;
import com.example.groupassessment.request_param.borrower.UpdateReqParam;
import com.example.groupassessment.response.BorrowerView;

import java.util.List;

public interface BorrowerService {
    List<Borrower> index();
    Borrower show(Long id);
    Borrower update(Long id, Borrower borrower);
    Borrower create(CreateReqParam borrower);
    BorrowerView<Borrower> delete(Long id);
}
