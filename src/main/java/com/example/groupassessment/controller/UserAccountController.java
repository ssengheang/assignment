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
@RequestMapping("/User")
public class UserController {
    private UserServiceImp userServiceImp;
    @Autowired
    public UserController(UserServiceImp userServiceImp){
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("")
    public User createUser(@Validated @RequestBody CreateReqParam borrower_params){
        return userServiceImp.create(user_params);
    }

    @GetMapping("")
    public List<User> listUser(){
        return userServiceImp.index();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") Long id){
        return userServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam user_params){
        return userServiceImp.update(id, user_params);
    }
//
//    @DeleteMapping("/{id}")
//    public BorrowerView<Borrower> deleteBorrower(@PathVariable(name = "id") Long id){
//        return borrowerServiceImp.delete(id);
//    }