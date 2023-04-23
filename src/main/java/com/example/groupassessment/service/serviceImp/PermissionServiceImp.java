package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.account.Permission;
import com.example.groupassessment.repository.PermissionRepo;
import com.example.groupassessment.request_param.permission.ReqParam;
import com.example.groupassessment.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class PermissionServiceImp implements PermissionService {
    private PermissionRepo permissionRepo;
    @Autowired
    public PermissionServiceImp(PermissionRepo permissionRepo){
        this.permissionRepo = permissionRepo;
    }
    @Override
    public List<Permission> index() {
        return permissionRepo.findAll();
    }

    @Override
    public Permission show(Long id) {
        return permissionRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
    public String delete(Long id) {
        Permission permission1 = permissionRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        permissionRepo.delete(permission1);
        return "Deleted";
    }

    @Override
    public Permission update(Long id, ReqParam permission) {
        Permission permission1 = permissionRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        permission1.setFeature(permission.getFeature());
        permission1.setActions(permission.getActions());
        return permissionRepo.save(permission1);
    }

    @Override
    public Permission create(ReqParam permission) {
        Permission permission1 = new Permission();
        permission1.setFeature(permission.getFeature());
        permission1.setActions(permission.getActions());
        return permissionRepo.save(permission1);
    }
}