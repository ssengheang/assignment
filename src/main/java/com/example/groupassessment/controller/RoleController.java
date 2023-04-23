package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.request_param.role.*;
import com.example.groupassessment.service.serviceImp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleServiceImp roleServiceImp;
    @Autowired
    public RoleController(RoleServiceImp roleServiceImp){
        this.roleServiceImp = roleServiceImp;
    }

    @PostMapping("")
    public Role createRole(@Validated @RequestBody ReqParam role){
        return roleServiceImp.create(role);
    }

    @GetMapping("")
    public List<Role> listRole(){
        return roleServiceImp.index();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable(name = "id") Long id){
        return roleServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable(name = "id") Long id, @Validated @RequestBody ReqParam role){
        return roleServiceImp.update(id, role);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable(name = "id") Long id){
        return roleServiceImp.delete(id);
    }
}
