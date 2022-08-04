package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.entity.User;
import com.ezyfox.cvconnect.exception.NotFoundException;
import com.ezyfox.cvconnect.model.AddAgencyData;
import com.ezyfox.cvconnect.model.EditAgencyData;
import com.ezyfox.cvconnect.repository.UserRepository;
import com.ezyfox.cvconnect.response.UserAgencyResponse;
import com.ezyfox.cvconnect.service.UserAgencyService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EzySingleton
@AllArgsConstructor
public class UserAgencyServiceImpl implements UserAgencyService {

    private final UserRepository userRepository;
    private final DataToEntityConverter dataToEntityConverter;

    @Override
    public void registerAgencyUser(AddAgencyData addAgencyData) {
        userRepository.save(dataToEntityConverter.dataToAgencyUser(addAgencyData));
    }

    @Override
    public void editAgencyUser(EditAgencyData editAgencyData) {
        User userById = userRepository.findById(editAgencyData.getId());
        if (userById == null) {
            throw new NotFoundException("User By Id not found");
        }
        if (!userById.getStatus().equals(UserStatus.ACTIVE)) {
            throw new NotFoundException("User By Id not active");
        }
        userById.setUpdatedTime(LocalDateTime.now());
        userById.setUsername(editAgencyData.getUserName());
        userById.setRoleId(editAgencyData.getRoleId());
        userById.setCompanyId(editAgencyData.getCompanyId());
        userById.setDescription(editAgencyData.getDescription());
        userById.setInformation(editAgencyData.getInformation());
        userById.setBirthDay(editAgencyData.getBirthDay());
        userById.setName(editAgencyData.getName());
        userById.setUsername(editAgencyData.getUserName());
        userById.setPassword(editAgencyData.getPassword());
        // userById.setTypeId();
        userById.setStatus(UserStatus.ACTIVE);
        userById.setStar(editAgencyData.getStar());
        userRepository.save(userById);
    }

    @Override
    public List<UserAgencyResponse> getAllAgency() {
        return null;
    }

    @Override
    public List<UserAgencyResponse> getAllAgencyActive() {
        return null;
    }

    @Override
    public List<UserAgencyResponse> getAgencyActiveByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public List<UserAgencyResponse> getAllAgencyByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public List<UserAgencyResponse> getAllAgencyByRoleId(long roleId) {
        return null;
    }

    @Override
    public List<UserAgencyResponse> getAgencyActiveByRoleId(long roleId) {
        return null;
    }

    @Override
    public UserAgencyResponse getAgencyById(long id) {
        return null;
    }

    @Override
    public UserAgencyResponse getAgencyActiveById(long id) {
        return null;
    }

    @Override
    public UserAgencyResponse getAgencyByUserName(String userName) {
        return null;
    }

    @Override
    public UserAgencyResponse getAgencyActiveByUserName(String userName) {
        return null;
    }
}
