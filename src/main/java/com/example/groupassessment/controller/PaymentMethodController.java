package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.PaymentMethod;
import com.example.groupassessment.repository.PaymentMethodRepo;
import com.example.groupassessment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentmethod")
public class PaymentMethodController {
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private PaymentMethodController(PaymentMethodRepo paymentMethodRepo){
        this.paymentMethodRepo = paymentMethodRepo;
    }

    @GetMapping("")
    public List<PaymentMethod> getAll(){
        return paymentMethodRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getOne(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));

        return ResponseEntity.ok().body(paymentMethod);
    }

    @PostMapping("")
    public ResponseEntity<PaymentMethod> create(@Validated @RequestBody PaymentMethod paymentMethod_params){
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setRepayment(paymentMethod_params.getCustomer());
        PaymentMethod paymentMethod_inserted = paymentMethodRepo.save(paymentMethod);
        return ResponseEntity.status(201).body(paymentMethod_inserted);
    }
        @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> update(@PathVariable(value = "id") Long id, @Validated @RequestBody PaymentMethod paymentMethod_params) throws ResourceNotFoundException {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));
        paymentMethod.setCustomer(paymentMethod_params.getCustomer());
        PaymentMethod updated_paymentMethod = paymentMethodRepo.save((paymentMethod));
        return ResponseEntity.ok().body(updated_paymentMethod);
    }
}
