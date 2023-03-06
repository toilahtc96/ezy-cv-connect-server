package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddAgencyData;
import com.ezyfox.cvconnect.model.EditUserData;
import com.ezyfox.cvconnect.response.UserAgencyResponse;

import java.util.List;

public interface UserAgencyService {

    void registerAgencyUser(AddAgencyData addAgencyData);

    void editAgencyUser(EditUserData editAgencyData);

    List<UserAgencyResponse> getAllAgency();

    List<UserAgencyResponse> getAllAgencyActive();

    List<UserAgencyResponse> getAgencyActiveByCompanyId(Long companyId);

    List<UserAgencyResponse> getAllAgencyByCompanyId(Long companyId);

    List<UserAgencyResponse> getAllAgencyByRoleId(long roleId);

    List<UserAgencyResponse> getAgencyActiveByRoleId(long roleId);

    UserAgencyResponse getAgencyById(long id);

    UserAgencyResponse getAgencyActiveById(long id);

    UserAgencyResponse getAgencyByUserName(String userName);

    UserAgencyResponse getAgencyActiveByUserName(String userName);
}
