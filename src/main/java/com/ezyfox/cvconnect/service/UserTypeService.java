package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.model.AddUserTypeData;
import com.ezyfox.cvconnect.model.EditUserTypeData;
import com.ezyfox.cvconnect.response.UserTypeResponse;

import java.util.List;

public interface UserTypeService {

    void saveUserType(AddUserTypeData data);

    void editUserType(EditUserTypeData data);

    UserTypeResponse getUserTypeByCode(UserTypeCode code);

    List<UserTypeResponse> getUserTypeByMeaning(String meaning);

    List<UserTypeResponse> getAllUserType();

    List<UserTypeResponse> getAllUserTypeActive();

    UserTypeResponse getById(long id);
}
