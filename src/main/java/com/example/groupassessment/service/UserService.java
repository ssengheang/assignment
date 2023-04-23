package com.example.groupassessment.service;

import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.request_param.user.*;

import java.util.List;

public interface UserService {
    List<User> index();
    User show(Long id);
    User update(Long id, UpdateReqParam user);
    User create(CreateReqParam user);
    User activate(Long id);
    User deactivate(Long id);
    String update_password(Long id, UpdatePasswordParam password);
}
