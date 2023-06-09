package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.enitity.PaymentMethod;
import com.example.groupassessment.enitity.projection.PaymentMethodProjection;
import com.example.groupassessment.enitity.projection.PermissionProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.BankRepo;
import com.example.groupassessment.repository.PaymentMethodRepo;
import com.example.groupassessment.request_param.payment_method.CreateReqParam;
import com.example.groupassessment.request_param.payment_method.UpdateReqParam;
import com.example.groupassessment.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class PaymentMethodServiceImp implements PaymentMethodService {
    private PaymentMethodRepo paymentMethodRepo;
    private BankRepo bankRepo;
    @Autowired
    public PaymentMethodServiceImp(PaymentMethodRepo paymentMethodRepo, BankRepo bankRepo){
        this.paymentMethodRepo = paymentMethodRepo;
        this.bankRepo = bankRepo;
    }
    @Override
    public List<PaymentMethodProjection> index(Pagination pagination) {
        Page<PaymentMethodProjection> paymentMethodProjections = paymentMethodRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(paymentMethodProjections.getTotalElements());
        return paymentMethodProjections.getContent();
    }

    @Override
    public PaymentMethodProjection show(Long id) {
        return paymentMethodRepo.findPaymentMethodProjectionById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
    public PaymentMethod update(Long id, UpdateReqParam paymentMethod) {
        PaymentMethod paymentMethod1 = paymentMethodRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));

        paymentMethod1.setMethodName(paymentMethod.getMethodName());
        return paymentMethodRepo.save(paymentMethod1);
    }

    @Override
    public PaymentMethod create(CreateReqParam paymentMethod) {
        PaymentMethod paymentMethod1 = new PaymentMethod();
        Bank bank = bankRepo.findById(paymentMethod.getBankId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (No bank resource found!)"));

        paymentMethod1.setBank(bank);
        paymentMethod1.setMethodName(paymentMethod.getMethodName());
        return paymentMethodRepo.save(paymentMethod1);
    }

    @Override
    public Boolean delete(Long id) {
        PaymentMethod paymentMethod1 = paymentMethodRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        paymentMethodRepo.delete(paymentMethod1);
        return true;
    }
}
