package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.account.Permission;
import com.example.groupassessment.enitity.projection.PermissionProjection;
import com.example.groupassessment.enitity.projection.RemarkProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.permission.ReqParam;
import com.example.groupassessment.service.serviceImp.PermissionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private PermissionServiceImp permissionServiceImp;
    @Autowired
    public PermissionController(PermissionServiceImp permissionServiceImp){
        this.permissionServiceImp = permissionServiceImp;
    }

    @PostMapping("")
    public ApiResponse createPermission(@Validated @RequestBody ReqParam permission){
        Permission permission1 = permissionServiceImp.create(permission);
        PermissionProjection permissionProjection = permissionServiceImp.show(permission1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                permissionProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listPermission(Pagination pagination){
        List<PermissionProjection> permission = permissionServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", permission);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getPermissionById(@PathVariable(name = "id") Long id){
        PermissionProjection permissionProjection = permissionServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                permissionProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updatePermission(@PathVariable(name = "id") Long id, @Validated @RequestBody ReqParam permission){
        Permission permission1 = permissionServiceImp.update(id, permission);
        PermissionProjection permissionProjection = permissionServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                permissionProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePermission(@PathVariable(name = "id") Long id){
        Boolean isDeleted = permissionServiceImp.delete(id);
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
