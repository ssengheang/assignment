package com.example.groupassessment.enitity.address;

import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "villages")
public class Village extends BaseEntity {
    @Column(name = "village_name", length = 100, nullable = false)
    private String villageName;

    @ManyToOne
    @JoinColumn(name = "commune_id", nullable = false, insertable = false, updatable = false)
    private Commune commune;
}
