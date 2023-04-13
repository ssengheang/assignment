
package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.request_param.user.CreateReqParam;
import com.example.groupassessment.request_param.user.UpdateReqParam;
import com.example.groupassessment.response.UserView;
import com.example.groupassessment.service.serviceImp.UserServiceImp;
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
    public User createUser(@Validated @RequestBody CreateReqParam user_params){
        return userServiceImp.create(borrower_params);
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

    @DeleteMapping("/{id}")
    public UserView<User> deleteUser(@PathVariable(name = "id") Long id){
        return userServiceImp.delete(id);
    }
}