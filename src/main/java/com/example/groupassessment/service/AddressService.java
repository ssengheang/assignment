package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Address;
import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.request_param.address.ReqParam;

public interface AddressService {
    void create(ReqParam address, Borrower borrower);
    public void update(Long id, ReqParam address);
}
