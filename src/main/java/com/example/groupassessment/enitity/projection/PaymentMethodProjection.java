package com.example.groupassessment.enitity.projection;

import com.example.groupassessment.enitity.Bank;

public interface PaymentMethodProjection {
    Long getId();
    String getMethodName();
    BankProjection getBank();
}
