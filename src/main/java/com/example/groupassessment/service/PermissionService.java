package com.example.groupassessment.service;

import com.example.groupassessment.enitity.account.Permission;
import com.example.groupassessment.enitity.projection.PermissionProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.permission.ReqParam;

import java.util.List;

public interface PermissionService {
    List<PermissionProjection> index(Pagination pagination);
    PermissionProjection show(Long id);
    Boolean delete(Long id);
    Permission update(Long id, ReqParam permission);
    Permission create(ReqParam permission);
}
