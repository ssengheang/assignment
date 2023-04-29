package com.example.groupassessment.enitity.projection;

import jakarta.persistence.Column;

import java.util.Date;

public interface RepaymentProjection {
    Long getId();
    Date getDate();
    Float getAmount();
    Float getInterestDue();
    LoanProjection getLoan();
}
