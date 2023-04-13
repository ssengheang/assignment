package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.request_param.borrower.CreateReqParam;
import com.example.groupassessment.request_param.borrower.UpdateReqParam;
import com.example.groupassessment.response.BorrowerView;
import com.example.groupassessment.service.serviceImp.BorrowerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user_accounts")

public class UserAccountsController{
    private UserAccountServiceImp useraccountServiceImp;
    @Autowired
    public UserAccountsController(UseraccountServiceImp useraccountServiceImpServiceImp){
        this.useraccountServiceImp = useraccountServiceImp;
    }
    @GetMapping("")
    public List<User> listUser(){
        return userServiceImp.index();
    }

}