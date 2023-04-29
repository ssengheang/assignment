package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.enitity.projection.RoleProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
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
    public List<RoleProjection> listRole(){
        return roleServiceImp.index();
    }

    @GetMapping("/{id}")
    public ApiResponse getRoleById(@PathVariable(name = "id") Long id){
        RoleProjection roleProjection = roleServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                roleProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateRole(@PathVariable(name = "id") Long id, @Validated @RequestBody ReqParam role){
        Role role1 = roleServiceImp.update(id, role);
        RoleProjection roleProjection = roleServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                roleProjection
        );
    }

    @PutMapping("/{id}/set-permission")
    public ApiResponse setPermission(@PathVariable(name = "id") Long id, @RequestBody SetPermission feature){
        Role role = roleServiceImp.set_permission(id, feature);
        RoleProjection roleProjection = roleServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                roleProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteRole(@PathVariable(name = "id") Long id){
        Boolean isDeleted = roleServiceImp.delete(id);
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
