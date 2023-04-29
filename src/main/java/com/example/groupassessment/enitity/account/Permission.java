package com.example.groupassessment.enitity.account;

import com.example.groupassessment.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private List<String> actions;

//    @JsonManagedReference
    @ManyToMany(mappedBy = "permissions")
    List<Role> roles;

    @PreRemove
    private void removePermissionsFromRole() {
        for (Role o : roles) {
            o.getPermissions().remove(this);
        }
    }
}
