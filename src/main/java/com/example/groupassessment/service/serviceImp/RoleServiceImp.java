package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.account.Permission;
import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.enitity.projection.RoleProjection;
import com.example.groupassessment.enitity.projection.TypeProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.PermissionRepo;
import com.example.groupassessment.repository.RoleRepo;
import com.example.groupassessment.request_param.role.ReqParam;
import com.example.groupassessment.request_param.role.SetPermission;
import com.example.groupassessment.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private RoleRepo roleRepo;
    private PermissionRepo permissionRepo;
    @Autowired
    public RoleServiceImp(RoleRepo roleRepo, PermissionRepo permissionRepo){
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
    }


    @Override
    public List<RoleProjection> index(Pagination pagination) {
        Page<RoleProjection> roleProjections = roleRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(roleProjections.getTotalElements());
        return roleProjections.getContent();
    }

    @Override
    public RoleProjection show(Long id) {
        return roleRepo.findRoleProjectionById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!)"));
    }

    @Override
    public Role update(Long id, ReqParam role) {
        Role role1 = roleRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!)"));
        role1.setTitle(role.getTitle());
        return roleRepo.save(role1);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Role role = roleRepo.findById(id)
                    .orElseThrow(() -> new ResourceAccessException("No resource found!)"));
            roleRepo.delete(role);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Role create(ReqParam role) {
        Role role1 = new Role();
        role1.setTitle(role.getTitle());
        return roleRepo.save(role1);
    }

    @Override
    public Role set_permission(Long id, SetPermission feature) {
        Role role1 = roleRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!)"));
        List<Permission> permissions = new ArrayList<>();
        for (String permission_feature : feature.getFeatures()){
            Permission permission = permissionRepo.findByFeature(permission_feature);
            if(!ObjectUtils.isEmpty(permission)){
                permissions.add(permission);
            }else{
                System.out.println("Permission not found!");
            }
        }
        role1.setPermissions(permissions);
        return roleRepo.save(role1);
    }


}
