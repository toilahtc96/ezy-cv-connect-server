package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.request.*;
import com.ezyfox.cvconnect.util.DateUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.text.ParseException;

@EzySingleton
public class RequestToDataConverter {

    public LoginData toDataFromLogin(LoginRequest loginRequest) {
        return
            LoginData
                .builder()
                .username(loginRequest.getUsername())
                .password(loginRequest.getPassword())
                .build();
    }

    public RegisterData toDataFromRegister(RegisterRequest registerRequest) {
        try {
            return RegisterData
                .builder()
                .birthDay(DateUtil.parseFromStringFormat(
                    registerRequest.getBirthDay(),
                    DateUtil.DATE_DDMMYYYY_PATTERN
                ))
                .name(registerRequest.getName())
                .cvLink(registerRequest.getCvLink())
                .levelId(registerRequest.getLevelId())
                .status(registerRequest.getStatus())
                .typeId(registerRequest.getTypeId())
                .password(registerRequest.getPassword())
                .yearExperience(registerRequest.getYearExperience())
                .username(registerRequest.getUsername())
                .build();
        } catch (ParseException parseException) {
            return RegisterData
                .builder()
                .name(registerRequest.getName())
                .cvLink(registerRequest.getCvLink())
                .levelId(registerRequest.getLevelId())
                .status(registerRequest.getStatus())
                .typeId(registerRequest.getTypeId())
                .password(registerRequest.getPassword())
                .yearExperience(registerRequest.getYearExperience())
                .username(registerRequest.getUsername())
                .build();
        }

    }

    public AddAddressData toDataFromAddAddress(AddAddressRequest addAddressRequest) {
        return AddAddressData
            .builder()
            .parentId(addAddressRequest.getParentId())
            .type(addAddressRequest.getType())
            .name(addAddressRequest.getName())
            .build();
    }

    public AddressData toDataFromEditAddress(EditAddressRequest editAddressRequest) {
        return AddressData
            .builder()
            .id(editAddressRequest.getId())
            .name(editAddressRequest.getName())
            .parentId(editAddressRequest.getParentId())
            .type(editAddressRequest.getType())
            .build();
    }

    public AddRoleData toDataFromAddRole(AddRoleRequest addRoleRequest) {
        return AddRoleData
            .builder()
            .code(addRoleRequest.getCode())
            .name(addRoleRequest.getName())
            .build();
    }

    public RoleData toDataFromEditRole(RoleRequest editRoleRequest) {
        return RoleData
            .builder()
            .id(editRoleRequest.getId())
            .name(editRoleRequest.getName())
            .code(editRoleRequest.getCode())
            .status(editRoleRequest.getStatus())
            .build();
    }

    public AddUserTypeData toDataFromAddUserType(AddUserTypeRequest addUserTypeRequest) {
        return AddUserTypeData
            .builder()
            .code(addUserTypeRequest.getCode())
            .meaning(addUserTypeRequest.getMeaning())
            .build();
    }

    public EditUserTypeData toDataFromEditUserType(EditUserTypeRequest editUserTypeRequest) {
        return EditUserTypeData
            .builder()
            .userTypeId(editUserTypeRequest.getId())
            .code(editUserTypeRequest.getCode())
            .meaning(editUserTypeRequest.getMeaning())
            .status(editUserTypeRequest.getStatus())
            .build();
    }

    public AddAgencyData toDataFromAddAgency(AddAgencyUserRequest addAgencyUserRequest) {
        try {
            return AddAgencyData
                .builder()
                .companyId(addAgencyUserRequest.getCompanyId())
                .information(addAgencyUserRequest.getInformation())
                .description(addAgencyUserRequest.getDescription())
                .birthDay(
                    DateUtil.parseFromStringFormat(
                        addAgencyUserRequest.getBirthDay(), DateUtil.DATE_DDMMYYYY_PATTERN
                    )
                )
                .status(UserStatus.ACTIVE)
                .star(-1)
                .userName(addAgencyUserRequest.getUserName())
                .password(addAgencyUserRequest.getPassword())
                .name(addAgencyUserRequest.getName())
                .build();
        } catch (ParseException e) {
            return AddAgencyData
                .builder()
                .companyId(addAgencyUserRequest.getCompanyId())
                .information(addAgencyUserRequest.getDescription())
                .description(addAgencyUserRequest.getInformation())
                .status(UserStatus.ACTIVE)
                .star(-1)
                .userName(addAgencyUserRequest.getUserName())
                .password(addAgencyUserRequest.getPassword())
                .name(addAgencyUserRequest.getName())
                .build();
        }
    }

    public EditAgencyData toDataFromAddAgency(EditAgencyUserRequest editAgencyUserRequest) {
        try {
            return EditAgencyData
                .builder()
                .companyId(editAgencyUserRequest.getCompanyId())
                .roleId(editAgencyUserRequest.getRoleId())
                .typeId(editAgencyUserRequest.getTypeId())
                .information(editAgencyUserRequest.getDescription())
                .description(editAgencyUserRequest.getInformation())
                .birthDay(
                    DateUtil.parseFromStringFormat(
                        editAgencyUserRequest.getBirthDay(), DateUtil.DATE_DDMMYYYY_PATTERN
                    )
                )
                .status(editAgencyUserRequest.getStatus())
                .star(editAgencyUserRequest.getStar())
                .userName(editAgencyUserRequest.getUserName())
                .password(editAgencyUserRequest.getPassword())
                .name(editAgencyUserRequest.getName())
                .build();
        } catch (ParseException e) {
            return EditAgencyData
                .builder()
                .companyId(editAgencyUserRequest.getCompanyId())
                .roleId(editAgencyUserRequest.getRoleId())
                .typeId(editAgencyUserRequest.getTypeId())
                .information(editAgencyUserRequest.getDescription())
                .description(editAgencyUserRequest.getInformation())
                .status(editAgencyUserRequest.getStatus())
                .star(editAgencyUserRequest.getStar())
                .userName(editAgencyUserRequest.getUserName())
                .password(editAgencyUserRequest.getPassword())
                .name(editAgencyUserRequest.getName())
                .build();
        }
    }

    public AddUserRoleData toDataFromAddUserRole(AddUserRoleRequest addUserRoleRequest) {
        return AddUserRoleData
            .builder()
            .userId(addUserRoleRequest.getUserId())
            .roleId(addUserRoleRequest.getRoleId())
            .status(EntityStatus.ACTIVE)
            .build();
    }

    public EditUserRoleData toDataFromEditUserRole(EditUserRoleRequest editUserRoleRequest) {
        return EditUserRoleData
            .builder()
            .userRoleId(editUserRoleRequest.getUserRoleId())
            .userId(editUserRoleRequest.getUserId())
            .roleId(editUserRoleRequest.getRoleId())
            .build();
    }

    public AddProcessData toDataFromAddProcess(AddProcessRequest addProcessRequest) {
        return AddProcessData
            .builder()
            .processCode(addProcessRequest.getProcessCode())
            .meaning(addProcessRequest.getMeaning())
            .build();
    }

    public EditProcessData toDataFromEditProcess(EditProcessRequest editProcessRequest) {
        return EditProcessData
            .builder()
            .processId(editProcessRequest.getProcessId())
            .processCode(editProcessRequest.getProcessCode())
            .meaning(editProcessRequest.getMeaning())
            .build();
    }

    public AddDealData toDataFromAddDeal(AddDealRequest addDealRequest) {
        return AddDealData
            .builder()
            .agencyId(addDealRequest.getAgencyId())
            .candidateId(addDealRequest.getCandidateId())
            .processId(addDealRequest.getProcessId())
            .status(addDealRequest.getStatus())
            .build();
    }

    public EditDealData toDataFromEditDeal(EditDealRequest editDealRequest) {
        return EditDealData
            .builder()
            .dealId(editDealRequest.getDealId())
            .agencyId(editDealRequest.getAgencyId())
            .candidateId(editDealRequest.getCandidateId())
            .processId(editDealRequest.getProcessId())
            .status(editDealRequest.getStatus())
            .build();
    }
}
