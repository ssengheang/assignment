package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "borrower_images")
public class BorrowerImage extends BaseEntity {
    @Column(name = "path", length = 255, nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false, insertable = false, updatable = false)
    private Borrower borrower;
}