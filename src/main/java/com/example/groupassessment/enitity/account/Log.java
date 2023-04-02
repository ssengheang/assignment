package com.example.groupassessment.enitity.account;

import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "logs")
public class Log extends BaseEntity {
    @Column(name = "feature", length = 50, nullable = false)
    private String feature;
    @Column(name = "status", length = 50, nullable = false)
    private String status;
    @Column(name = "entity_type", length = 50, nullable = false)
    private String entity_type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User user;
}
