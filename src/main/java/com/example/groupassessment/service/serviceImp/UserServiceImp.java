package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.enitity.projection.UserProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.RoleRepo;
import com.example.groupassessment.repository.UserRepo;
import com.example.groupassessment.request_param.user.*;
import com.example.groupassessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImp implements UserService {
    private final PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder, UserRepo userRepo, RoleRepo roleRepo){
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    @Override
    public List<UserProjection> index(Pagination pagination) {
        Page<UserProjection> user = userRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(user.getTotalElements());
        return user.getContent();
    }

    @Override
    public UserProjection show(Long id) {
        return userRepo.findUserProjectionById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
    public User update(Long id, UpdateReqParam user) {
        User user1 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());
        return userRepo.save(user1);
    }

    @Override
    public User create(CreateReqParam user) {
        User user1 = new User();
        Role role = roleRepo.findById(user.getRoleId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (No Role resource found!)"));

        user1.setRole(role);
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user1);
    }

    @Override
    public User activate(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        if (user.isActive() == false){
            user.setActive(true);
            userRepo.save(user);
        }
        return user;
    }

    @Override
    public User deactivate(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        if (user.isActive() == true){
            user.setActive(false);
            userRepo.save(user);
        }
        return user;
    }

    @Override
    public Boolean update_password(Long id, UpdatePasswordParam password) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));

        Boolean is_match = Objects.equals(user.getPassword(), password.getOldPassword()) &&
                Objects.equals(password.getNewPassword(), password.getConfirmPassword());

        if (!is_match) {
            return false;
        }
        user.setPassword(password.getNewPassword());
        userRepo.save(user);
        return true;
    }
}
