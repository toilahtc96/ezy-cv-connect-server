package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddUserTypeData;
import com.ezyfox.cvconnect.model.EditUserTypeData;
import com.ezyfox.cvconnect.response.UserTypeResponse;

import java.util.List;

public interface UserTypeService {

    void saveUserType(AddUserTypeData data);

    void editUserType(EditUserTypeData data);

    List<UserTypeResponse> getUserTypeByCode(String code);

    List<UserTypeResponse> getUserTypeByMeaning(String meaning);

    List<UserTypeResponse> getAllUserType();

    List<UserTypeResponse> getAllUserTypeActive();

    UserTypeResponse getById(long id);
}
