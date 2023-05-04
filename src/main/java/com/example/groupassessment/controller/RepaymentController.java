package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Repayment;
import com.example.groupassessment.enitity.projection.RepaymentProjection;
import com.example.groupassessment.enitity.projection.RoleProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.repayment.CreateReqParam;
import com.example.groupassessment.service.RepaymentService;
import com.example.groupassessment.service.serviceImp.RepaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/repayments")
public class RepaymentController {
    private RepaymentService repaymentServiceImp;
    @Autowired
    public RepaymentController(RepaymentService repaymentServiceImp){
        this.repaymentServiceImp = repaymentServiceImp;
    }

    @PostMapping("")
    public ApiResponse createRepayment(@Validated @RequestBody CreateReqParam repayment){
        Repayment repayment1 = repaymentServiceImp.create(repayment);
        RepaymentProjection repaymentProjection = repaymentServiceImp.show(repayment1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                repaymentProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listRepayment(Pagination pagination){
        List<RepaymentProjection> repayment = repaymentServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", repayment);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getRepaymentById(@PathVariable(name = "id") Long id){
        RepaymentProjection repaymentProjection = repaymentServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                repaymentProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteRepayment(@PathVariable(name = "id") Long id){
        Boolean isDeleted = repaymentServiceImp.delete(id);
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
