package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.repository.RoleRepo;
import com.example.groupassessment.request_param.role.ReqParam;
import com.example.groupassessment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private RoleRepo roleRepo;
    @Autowired
    public RoleServiceImp(RoleRepo roleRepo){
        this.roleRepo = roleRepo;
    }


    @Override
    public List<Role> index() {
        return roleRepo.findAll();
    }

    @Override
    public Role show(Long id) {
        return roleRepo.findById(id)
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
    public String delete(Long id) {
        Role role = roleRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!)"));
        roleRepo.delete(role);
        return "Deleted";
    }

    @Override
    public Role create(ReqParam role) {
        Role role1 = new Role();
        role1.setTitle(role.getTitle());
        return roleRepo.save(role1);
    }
}
