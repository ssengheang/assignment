package com.example.groupassessment.enitity.projection;

import java.util.List;

public interface PermissionProjection {
    Long getId();
    String getFeature();
    List<String> getActions();
}
