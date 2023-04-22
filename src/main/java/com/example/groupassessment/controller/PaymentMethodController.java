package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.PaymentMethod;
import com.example.groupassessment.request_param.payment_method.*;
import com.example.groupassessment.service.serviceImp.PaymentMethodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
    private PaymentMethodServiceImp paymentMethodServiceImp;
    @Autowired
    public PaymentMethodController(PaymentMethodServiceImp paymentMethodServiceImp){
        this.paymentMethodServiceImp = paymentMethodServiceImp;
    }

    @PostMapping("")
    public PaymentMethod createPaymentMethod(@Validated @RequestBody CreateReqParam paymentMethod){
        return paymentMethodServiceImp.create(paymentMethod);
    }

    @GetMapping("")
    public List<PaymentMethod> listPaymentMethod(){
        return paymentMethodServiceImp.index();
    }

    @GetMapping("/{id}")
    public PaymentMethod getBankById(@PathVariable(name = "id") Long id){
        return paymentMethodServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public PaymentMethod updatePaymentMethod(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam paymentMethod){
        return paymentMethodServiceImp.update(id, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public String deletePaymentMethod(@PathVariable(name = "id") Long id){
        return paymentMethodServiceImp.delete(id);
    }
}
