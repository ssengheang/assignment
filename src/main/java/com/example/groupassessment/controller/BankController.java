package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.enitity.projection.BankProjection;
import com.example.groupassessment.enitity.projection.BorrowerProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.bank.ReqParam;
import com.example.groupassessment.service.BankService;
import com.example.groupassessment.service.serviceImp.BankServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/banks")
public class BankController {
    private BankService bankServiceImp;
    @Autowired
    public BankController(BankService bankServiceImp){
        this.bankServiceImp = bankServiceImp;
    }

    @PostMapping("")
    public ApiResponse createBank(@Validated @RequestBody ReqParam bank){
        Bank bank1 = bankServiceImp.create(bank);
        BankProjection bankProjection = bankServiceImp.show(bank1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                bankProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listBank(Pagination pagination){
        List<BankProjection> bankProjections = bankServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", bankProjections);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getBankById(@PathVariable(name = "id") Long id){
        BankProjection bankProjection = bankServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                bankProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateBank(@PathVariable(name = "id") Long id, @Validated @RequestBody ReqParam bank){
        Bank bank1 = bankServiceImp.update(id, bank);
        BankProjection bankProjection = bankServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                bankProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteBank(@PathVariable(name = "id") Long id){
        Boolean isDeleted = bankServiceImp.delete(id);
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
