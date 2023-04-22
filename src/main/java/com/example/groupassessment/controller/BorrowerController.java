package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Address;
import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.request_param.address.ReqParam;
import com.example.groupassessment.request_param.borrower.CreateReqParam;
import com.example.groupassessment.request_param.borrower.UpdateReqParam;
import com.example.groupassessment.response.BorrowerView;
import com.example.groupassessment.service.serviceImp.AddressServiceImp;
import com.example.groupassessment.service.serviceImp.BorrowerServiceImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private BorrowerServiceImp borrowerServiceImp;
    private AddressServiceImp addressServiceImp;
    @Autowired
    public BorrowerController(BorrowerServiceImp borrowerServiceImp, AddressServiceImp addressServiceImp){
        this.borrowerServiceImp = borrowerServiceImp;
        this.addressServiceImp = addressServiceImp;
    }

    @PostMapping("")
    public Borrower createBorrower(@RequestBody CreateReqParam borrower_params){
        Borrower borrower = borrowerServiceImp.create(borrower_params);
        for (ReqParam addressReq : borrower_params.getAddress()){
            addressServiceImp.create(addressReq, borrower);
        }
        return borrower;
    }

    @GetMapping("")
    public List<Borrower> listBorrower(){
        return borrowerServiceImp.index();
    }

    @GetMapping("/{id}")
    public Borrower getBorrowerById(@PathVariable(name = "id") Long id){
        return borrowerServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Borrower updateBorrower(@PathVariable(name = "id") Long id, @RequestBody UpdateReqParam borrower_params){
//        Borrower borrower = borrowerServiceImp.update(id, borrower_params);
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
        return borrowerServiceImp.update(id, borrower);
    }

    @DeleteMapping("/{id}")
    public BorrowerView<Borrower> deleteBorrower(@PathVariable(name = "id") Long id){
        return borrowerServiceImp.delete(id);
    }
}
