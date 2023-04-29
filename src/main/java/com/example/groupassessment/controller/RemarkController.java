package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Remark;
import com.example.groupassessment.enitity.projection.RemarkProjection;
import com.example.groupassessment.enitity.projection.RepaymentProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.remark.CreateReqParam;
import com.example.groupassessment.request_param.remark.UpdateReqParam;
import com.example.groupassessment.service.serviceImp.RemarkServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/remarks")
public class RemarkController {
    private RemarkServiceImp remarkServiceImp;
    @Autowired
    public RemarkController(RemarkServiceImp remarkServiceImp){
        this.remarkServiceImp = remarkServiceImp;
    }

    @PostMapping("")
    public Remark createRemark(@Validated @RequestBody CreateReqParam remark){
        return remarkServiceImp.create(remark);
    }

    @GetMapping("")
    public Map<String, Object> listRemark(Pagination pagination){
        List<RemarkProjection> remark = remarkServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", remark);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getRemarkById(@PathVariable(name = "id") Long id){
        RemarkProjection remarkProjection = remarkServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                remarkProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateRemark(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam remark){
        Remark remark1 = remarkServiceImp.update(id, remark);
        RemarkProjection remarkProjection = remarkServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                remarkProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteRemark(@PathVariable(name = "id") Long id){
        Boolean isDeleted = remarkServiceImp.delete(id);
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
