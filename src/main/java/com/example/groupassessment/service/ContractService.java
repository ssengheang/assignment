package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Contract;
import com.example.groupassessment.enitity.projection.ContractProjection;
import com.example.groupassessment.request_param.contract.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContractService {
    void save(MultipartFile file, ReqBorrowerId borrowerId, ReqLoanId loanId);
    Resource load(String filename);
    List<ContractProjection> index();
    ContractProjection show(Long id);
    Contract update(Long id, MultipartFile file);
    Boolean delete(Long id);
}
