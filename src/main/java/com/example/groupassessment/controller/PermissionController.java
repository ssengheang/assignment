package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.account.Permission;
import com.example.groupassessment.request_param.permission.ReqParam;
import com.example.groupassessment.service.serviceImp.PermissionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private PermissionServiceImp permissionServiceImp;
    @Autowired
    public PermissionController(PermissionServiceImp permissionServiceImp){
        this.permissionServiceImp = permissionServiceImp;
    }

    @PostMapping("")
    public Permission createPermission(@Validated @RequestBody ReqParam permission){
        return permissionServiceImp.create(permission);
    }

    @GetMapping("")
    public List<Permission> listPermission(){
        return permissionServiceImp.index();
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable(name = "id") Long id){
        return permissionServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable(name = "id") Long id, @Validated @RequestBody ReqParam permission){
        return permissionServiceImp.update(id, permission);
    }

    @DeleteMapping("/{id}")
    public String deletePermission(@PathVariable(name = "id") Long id){
        return permissionServiceImp.delete(id);
    }
}
