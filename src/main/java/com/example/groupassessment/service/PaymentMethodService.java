package com.example.groupassessment.service;

import com.example.groupassessment.enitity.PaymentMethod;
import com.example.groupassessment.request_param.payment_method.CreateReqParam;
import com.example.groupassessment.request_param.payment_method.UpdateReqParam;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethod> index();
    PaymentMethod show(Long id);
    PaymentMethod update(Long id, UpdateReqParam paymentMethod);
    PaymentMethod create(CreateReqParam paymentMethod);
    String delete(Long id);
}
