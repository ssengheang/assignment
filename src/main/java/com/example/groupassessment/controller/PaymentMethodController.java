package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.PaymentMethod;
import com.example.groupassessment.enitity.projection.PaymentMethodProjection;
import com.example.groupassessment.enitity.projection.PermissionProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.payment_method.*;
import com.example.groupassessment.service.serviceImp.PaymentMethodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
    private PaymentMethodServiceImp paymentMethodServiceImp;
    @Autowired
    public PaymentMethodController(PaymentMethodServiceImp paymentMethodServiceImp){
        this.paymentMethodServiceImp = paymentMethodServiceImp;
    }

    @PostMapping("")
    public ApiResponse createPaymentMethod(@Validated @RequestBody CreateReqParam paymentMethod){
        PaymentMethod paymentMethod1 = paymentMethodServiceImp.create(paymentMethod);
        PaymentMethodProjection paymentMethodProjection = paymentMethodServiceImp.show(paymentMethod1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                paymentMethodProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listPaymentMethod(Pagination pagination){
        List<PaymentMethodProjection> paymentMethodProjections = paymentMethodServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", paymentMethodProjections);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getBankById(@PathVariable(name = "id") Long id){
        PaymentMethodProjection paymentMethodProjection = paymentMethodServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                paymentMethodProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updatePaymentMethod(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam paymentMethod){
        PaymentMethod paymentMethod1 = paymentMethodServiceImp.update(id, paymentMethod);
        PaymentMethodProjection paymentMethodProjection = paymentMethodServiceImp.show(paymentMethod1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                paymentMethodProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePaymentMethod(@PathVariable(name = "id") Long id){
        Boolean isDeleted = paymentMethodServiceImp.delete(id);
        if (!isDeleted){
            return new ApiResponse<>(
                    ApiStatus.FAI_DELETED.getCode(),
                    ApiStatus.FAI_DELETED.getMessage()
            );
        }
        return new ApiResponse<>(
                ApiStatus.SUC_DELETED.getCode(),
                ApiStatus.SUC_DELETED.getMessage()
        );
    }
}
