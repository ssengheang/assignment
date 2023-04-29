package com.example.groupassessment.enitity.account;

import com.example.groupassessment.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username", length = 100, nullable = false)
    private String username;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Log> logs;
}
