package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.projection.BorrowerProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.borrower.CreateReqParam;

import java.util.List;

public interface BorrowerService {
    List<BorrowerProjection> index(Pagination pagination);
    BorrowerProjection show(Long id);
    Borrower update(Long id, Borrower borrower);
    Borrower create(CreateReqParam borrower);
    Boolean delete(Long id);
}
