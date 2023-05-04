package com.example.groupassessment.enitity.projection;

import jakarta.persistence.Column;

public interface BankAccountProjection {
    Long getId();
    String getKeyId();
    String getAccountName();
    BankProjection getBank();
    BorrowerProjection getBorrower();
}
