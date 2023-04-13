package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.exception.ResourceNotFoundException;
import com.example.groupassessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepo userRepo;
    @Autowired
    public UserController(UserServiceImp UserServiceImp){
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("")
    public User createUser(@Validated @RequestBody CreateReqParam user_params){
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

    @DeleteMapping("/{id}")
    public UserView<User> deleteUser(@PathVariable(name = "id") Long id){
        return userServiceImp.delete(id);
    }
}