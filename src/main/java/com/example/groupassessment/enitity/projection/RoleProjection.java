package com.example.groupassessment.enitity.projection;

import java.util.List;

public interface RoleProjection {
    Long getId();
    String getTitle();
    List<PermissionProjection> getPermissions();
}
