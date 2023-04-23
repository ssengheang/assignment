package com.example.groupassessment.service;

import com.example.groupassessment.enitity.account.Permission;
import com.example.groupassessment.request_param.permission.ReqParam;

import java.util.List;

public interface PermissionService {
    List<Permission> index();
    Permission show(Long id);
    String delete(Long id);
    Permission update(Long id, ReqParam permission);
    Permission create(ReqParam permission);
}
