package com.example.groupassessment.enitity.projection;

import com.example.groupassessment.enitity.Loan;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public interface RemarkProjection {
    Long getId();
    String getRemark();
    String getReason();
    LoanProjection getLoan();
}
