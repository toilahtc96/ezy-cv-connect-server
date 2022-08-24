package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddUserRoleData;
import com.ezyfox.cvconnect.model.EditUserRoleData;
import com.ezyfox.cvconnect.response.UserRoleResponse;

import java.util.List;

public interface UserRoleService {

    void saveUserRole(AddUserRoleData data);

    void editUserRole(EditUserRoleData data);

    List<UserRoleResponse> getUserRoleByRoleId(long roleId);

    List<UserRoleResponse> getUserRoleByUserId(long userId);

    List<UserRoleResponse> getAllUserRole();

    List<UserRoleResponse> getAllUserRoleActive();

    UserRoleResponse getById(long userRoleId);
}
