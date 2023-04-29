package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.projection.LoanProjection;
import com.example.groupassessment.enitity.projection.PaymentMethodProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.loan.CreateReqParam;
import com.example.groupassessment.request_param.loan.UpdateReqParam;
import com.example.groupassessment.response.LoanView;
import com.example.groupassessment.service.serviceImp.LoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private LoanServiceImp loanServiceImp;
    @Autowired
    public LoanController(LoanServiceImp loanServiceImp){
        this.loanServiceImp = loanServiceImp;
    }

    @PostMapping("")
    public ApiResponse createLoan(@Validated @RequestBody CreateReqParam loan_params){
        Loan loan = loanServiceImp.create(loan_params);
        LoanProjection loanProjection = loanServiceImp.show(loan.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                loanProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listLoan(Pagination pagination){
        List<LoanProjection> loanProjections = loanServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", loanProjections);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getLoanById(@PathVariable(name = "id") Long id){
        LoanProjection loanProjection = loanServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                loanProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateLoan(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam loan_params){
        Loan loan = loanServiceImp.update(id, loan_params);
        LoanProjection loanProjection = loanServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                loanProjection
        );
    }

    @PutMapping("/{id}/approve")
    public ApiResponse approve(@PathVariable(name = "id") Long id){
        Map<String, Boolean> loan = loanServiceImp.approve(id);
        if (loan.get("isPending") == true){
            LoanProjection loanProjection = loanServiceImp.show(id);
            return new ApiResponse<>(
                    ApiStatus.SUC_UPDATED.getCode(),
                    ApiStatus.SUC_UPDATED.getMessage(),
                    loanProjection
            );
        }else {
            return new ApiResponse<>(
                    ApiStatus.FAI_UPDATED.getCode(),
                    ApiStatus.FAI_UPDATED.getMessage(),
                    "Please make sure status is 'pending'"
            );
        }
    }

    @PutMapping("/{id}/reject")
    public ApiResponse reject(@PathVariable(name = "id") Long id){
        Map<String, Boolean> loan = loanServiceImp.reject(id);
        if (loan.get("isPending") == true){
            LoanProjection loanProjection = loanServiceImp.show(id);
            return new ApiResponse<>(
                    ApiStatus.SUC_UPDATED.getCode(),
                    ApiStatus.SUC_UPDATED.getMessage(),
                    loanProjection
            );
        }else {
            return new ApiResponse<>(
                    ApiStatus.FAI_UPDATED.getCode(),
                    ApiStatus.FAI_UPDATED.getMessage(),
                    "Please make sure status is 'pending'"
            );
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteLoan(@PathVariable(name = "id") Long id){
        Boolean isDeleted = loanServiceImp.delete(id);
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
