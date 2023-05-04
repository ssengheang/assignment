package com.example.groupassessment.enitity.projection;

public interface ContractProjection {
    Long getId();
    String getPath();
    LoanProjection getLoan();
}
