package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Address;
import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.projection.BorrowerImageProjection;
import com.example.groupassessment.enitity.projection.BorrowerProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.address.ReqParam;
import com.example.groupassessment.request_param.borrower.CreateReqParam;
import com.example.groupassessment.request_param.borrower.UpdateReqParam;
import com.example.groupassessment.response.BorrowerView;
import com.example.groupassessment.service.AddressService;
import com.example.groupassessment.service.BorrowerService;
import com.example.groupassessment.service.serviceImp.AddressServiceImp;
import com.example.groupassessment.service.serviceImp.BorrowerServiceImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/borrowers")
public class BorrowerController {
    private BorrowerService borrowerServiceImp;
    private AddressService addressServiceImp;
    @Autowired
    public BorrowerController(BorrowerService borrowerServiceImp, AddressService addressServiceImp){
        this.borrowerServiceImp = borrowerServiceImp;
        this.addressServiceImp = addressServiceImp;
    }

    @PostMapping("")
    public ApiResponse createBorrower(@RequestBody CreateReqParam borrower_params){
        Borrower borrower = borrowerServiceImp.create(borrower_params);
        for (ReqParam addressReq : borrower_params.getAddress()){
            addressServiceImp.create(addressReq, borrower);
        }

        BorrowerProjection borrowerProjection = borrowerServiceImp.show(borrower.getId());

        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                borrowerProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listBorrower(Pagination pagination){
        List<BorrowerProjection> borrowerProjections = borrowerServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", borrowerProjections);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getBorrowerById(@PathVariable(name = "id") Long id){
        BorrowerProjection borrowerProjection =  borrowerServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                borrowerProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateBorrower(@PathVariable(name = "id") Long id, @RequestBody UpdateReqParam borrower_params){
        Borrower borrower = new Borrower();
        BeanUtils.copyProperties(borrower_params, borrower);

//        List<Address> addressObj = new ArrayList<>();
        for (ReqParam addressReq : borrower_params.getAddress()){
            Address address = new Address();
            BeanUtils.copyProperties(addressReq, address);
//            addressObj.add(address);
            address.setBorrower(borrower);
//            addressServiceImp.update(addressReq, borrower);
            borrower.setAddress(address);
        }
        Borrower borrower1 = borrowerServiceImp.update(id, borrower);
        BorrowerProjection borrowerProjection = borrowerServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                borrowerProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteBorrower(@PathVariable(name = "id") Long id){
        Boolean isDeleted =  borrowerServiceImp.delete(id);
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
