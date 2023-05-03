package com.example.groupassessment.seeds;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.repository.RoleRepo;
import com.example.groupassessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeed {
    private final PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private String username = "admin";
    private String email = "admin@gmail.com";
    private String password = "admin123";
    private String role = "admin";
    @Autowired
    private UserSeed(PasswordEncoder passwordEncoder, UserRepo userRepo, RoleRepo roleRepo){
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    @EventListener
    public void seed(ContextRefreshedEvent event){
        try{
            User admin = userRepo.findByEmail(email).orElse(null);
            if (admin == null){
                if (roleRepo.findByTitle(role) == null){
                    Role role1 = new Role();
                    role1.setTitle(role);
                    roleRepo.save(role1);
                }
                Role role2 = roleRepo.findByTitle(role);
                if (role2 != null){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(passwordEncoder.encode(password));
                    user.setEmail(email);
                    user.setRole(role2);
                    userRepo.save(user);
                    System.out.println("\"" + email + "\"" + " seed inserted");
                }else {
                    System.out.println("\"" + email + "\"" + " seed failed");
                }

            }else {
                System.out.println("\"" + email + "\"" + " seed already existed!");
            }

        }catch(Exception e){
            System.out.println("Seeding data ran into problem! " + e.getMessage());
        }
    }
}
