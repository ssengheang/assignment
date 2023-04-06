package com.example.groupassessment.enitity.account;

import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "permissions")
public class Permission extends BaseEntity {
    @Column(name = "feature", length = 50, nullable = false)
    private String feature;

    @ManyToMany(mappedBy = "permissions")
    List<Role> roles;
}