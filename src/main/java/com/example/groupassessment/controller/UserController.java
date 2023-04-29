package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.enitity.projection.UserProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.user.*;
import com.example.groupassessment.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserServiceImp userServiceImp;
    @Autowired
    public UserController(UserServiceImp userServiceImp){
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("")
    public Map<String, Object> listUser(Pagination pagination){
        List<UserProjection> user = userServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", user);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getOneUser(@PathVariable(name = "id") Long id){
        UserProjection user =  userServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                user
        );
    }

    @PostMapping("")
    public ApiResponse createUser(@Validated @RequestBody CreateReqParam user){
        User user1 = userServiceImp.create(user);
        UserProjection userProjection = userServiceImp.show(user1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                userProjection
        );
    }

    @PutMapping("/{id}/activate")
    public ApiResponse activate(@PathVariable(name = "id") Long id){
        User user = userServiceImp.activate(id);
        UserProjection userProjection = userServiceImp.show((user.getId()));
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                userProjection
        );
    }

    @PutMapping("/{id}/deactivate")
    public ApiResponse deactivate(@PathVariable(name = "id") Long id){
        User user = userServiceImp.deactivate(id);
        UserProjection userProjection = userServiceImp.show((user.getId()));
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                userProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(name = "id") Long id, @RequestBody UpdateReqParam user){
        User user1 =userServiceImp.update(id, user);
        UserProjection userProjection = userServiceImp.show((user1.getId()));
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                userProjection
        );
    }

    @PutMapping("/{id}/update-password")
    public ApiResponse updatePassword(@PathVariable(name = "id") Long id, @RequestBody UpdatePasswordParam password) {
        Boolean isSuccess = userServiceImp.update_password(id, password);
        if (!isSuccess) {
            return new ApiResponse<>(
                    ApiStatus.FAI_UPDATED.getCode(),
                    ApiStatus.FAI_UPDATED.getMessage()
            );
        }
        UserProjection userProjection = userServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                userProjection
        );
    }
}
