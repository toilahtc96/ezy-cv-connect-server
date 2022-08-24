package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.UserType;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddUserTypeData;
import com.ezyfox.cvconnect.model.EditUserTypeData;
import com.ezyfox.cvconnect.repository.UserTypeRepository;
import com.ezyfox.cvconnect.response.UserTypeResponse;
import com.ezyfox.cvconnect.service.UserTypeService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;

    @Override
    public void saveUserType(AddUserTypeData data) {
        UserType userType = dataToEntityConverter.dataToUserType(data);
        userTypeRepository.save(userType);
    }

    @Override
    public void editUserType(EditUserTypeData data) {
        UserType userTypeById = userTypeRepository.findById(data.getUserTypeId());
        if (userTypeById == null) {
            throw new ResourceNotFoundException("UserType");
        }
        if (userTypeById.getStatus().equals(EntityStatus.ACTIVE)) {
            throw new ResourceNotFoundException("UserType By id Active");
        }
        if (data.getCode() != null) {
            userTypeById.setCode(UserTypeCode.of(data.getCode()));
        }
        userTypeById.setStatus(data.getStatus());
        if (data.getMeaning() != null) {
            userTypeById.setMeaning(data.getMeaning());
        }
        userTypeById.setUpdatedTime(LocalDateTime.now());
        userTypeRepository.save(userTypeById);
    }

    @Override
    public UserTypeResponse getUserTypeByCode(UserTypeCode code) {
        return entityToResponseConverter.toUserTypeResponse(userTypeRepository.getUserTypeByCode(code));
    }

    @Override
    public List<UserTypeResponse> getUserTypeByMeaning(String meaning) {
        return userTypeRepository.getUserTypeByMeaning(meaning)
            .stream()
            .map(entityToResponseConverter::toUserTypeResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<UserTypeResponse> getAllUserType() {
        return userTypeRepository.findAll()
            .stream()
            .map(entityToResponseConverter::toUserTypeResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<UserTypeResponse> getAllUserTypeActive() {
        return userTypeRepository.findByStatus(EntityStatus.ACTIVE)
            .stream()
            .map(entityToResponseConverter::toUserTypeResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserTypeResponse getById(long id) {
        return entityToResponseConverter.toUserTypeResponse(userTypeRepository.findById(id));
    }
}
