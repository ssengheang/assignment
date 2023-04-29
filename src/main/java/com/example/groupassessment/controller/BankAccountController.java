package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.BankAccount;
import com.example.groupassessment.enitity.projection.BankAccountProjection;
import com.example.groupassessment.enitity.projection.BankProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.bank_account.*;
import com.example.groupassessment.service.serviceImp.BankAccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {
    private BankAccountServiceImp bankAccountServiceImp;
    @Autowired
    public BankAccountController(BankAccountServiceImp bankAccountServiceImp){
        this.bankAccountServiceImp = bankAccountServiceImp;
    }

    @PostMapping("")
    public ApiResponse createBankAccount(@Validated @RequestBody CreateReqParam bankAccount){
        BankAccount bankAccount1 = bankAccountServiceImp.create(bankAccount);
        BankAccountProjection bankAccountProjection = bankAccountServiceImp.show(bankAccount1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                bankAccountProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listBankAccount(Pagination pagination){
        List<BankAccountProjection> bankAccountProjections = bankAccountServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", bankAccountProjections);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getBankById(@PathVariable(name = "id") Long id){
        BankAccountProjection bankAccountProjection = bankAccountServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                bankAccountProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateBankAccount(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam bankAccount){
        BankAccount bankAccount1 = bankAccountServiceImp.update(id, bankAccount);
        BankAccountProjection bankAccountProjection = bankAccountServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                bankAccountProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteBankAccount(@PathVariable(name = "id") Long id){
        Boolean isDeleted = bankAccountServiceImp.delete(id);
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
