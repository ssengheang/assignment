package com.example.groupassessment.service;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.enitity.projection.RoleProjection;
import com.example.groupassessment.request_param.role.ReqParam;
import com.example.groupassessment.request_param.role.SetPermission;

import java.util.List;

public interface RoleService {
    List<RoleProjection> index();
    RoleProjection show(Long id);
    Role update(Long id, ReqParam role);
    Boolean delete(Long id);
    Role create(ReqParam role);
    Role set_permission(Long id, SetPermission feature);

}
