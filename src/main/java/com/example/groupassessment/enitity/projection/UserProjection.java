package com.example.groupassessment.enitity.projection;

import java.time.LocalDateTime;

public interface UserProjection {
    Long getId();
    String getUsername();
    String getEmail();
    String getPassword();

    boolean getIsActive();

    RoleProjection getRole();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
