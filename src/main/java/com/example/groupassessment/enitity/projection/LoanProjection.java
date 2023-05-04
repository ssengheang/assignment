package com.example.groupassessment.enitity.projection;

import com.example.groupassessment.enitity.enum_data_type.LoanStatus;

import java.util.Date;

public interface LoanProjection {
    Long getId();
    Float getLoanAmount();
    Date getDate();
    Integer getInterest();
    Float getAmountLeft();
    LoanStatus getStatus();
    BorrowerProjection getBorrower();
}
