package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.request_param.role.ReqParam;
import com.example.groupassessment.request_param.user.*;
import com.example.groupassessment.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserServiceImp userServiceImp;
    @Autowired
    public UserController(UserServiceImp userServiceImp){
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("")
    public List<User> listUser(){
        return userServiceImp.index();
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable(name = "id") Long id){
        return userServiceImp.show(id);
    }

    @PostMapping("")
    public User createUser(@Validated @RequestBody CreateReqParam user){
        return userServiceImp.create(user);
    }

    @PutMapping("/{id}/activate")
    public User activate(@PathVariable(name = "id") Long id){
        return userServiceImp.activate(id);
    }

    @PutMapping("/{id}/deactivate")
    public User deactivate(@PathVariable(name = "id") Long id){
        return userServiceImp.deactivate(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable(name = "id") Long id, @RequestBody UpdateReqParam user){
        return userServiceImp.update(id, user);
    }

    @PutMapping("/{id}/update-password")
    public String updatePassword(@PathVariable(name = "id") Long id, @RequestBody UpdatePasswordParam password) {
        return userServiceImp.update_password(id, password);
    }
}
