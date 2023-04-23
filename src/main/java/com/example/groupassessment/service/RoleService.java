package com.example.groupassessment.service;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.request_param.role.ReqParam;

import java.util.List;

public interface RoleService {
    List<Role> index();
    Role show(Long id);
    Role update(Long id, ReqParam role);
    String delete(Long id);
    Role create(ReqParam role);
}
