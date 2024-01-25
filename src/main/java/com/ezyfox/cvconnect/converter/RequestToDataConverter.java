package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.*;
import com.ezyfox.cvconnect.entity.Level;
import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.repository.LevelRepository;
import com.ezyfox.cvconnect.repository.RoleRepository;
import com.ezyfox.cvconnect.request.*;
import com.ezyfox.cvconnect.service.UserTypeService;
import com.ezyfox.cvconnect.util.DateUtil;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.io.EzyStrings;
import lombok.AllArgsConstructor;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@EzySingleton
@AllArgsConstructor
public class RequestToDataConverter {

    private final UserTypeService userTypeService;
    private final RoleRepository roleRepository;
    private final LevelRepository levelRepository;

    public LoginData toDataFromLogin(LoginRequest loginRequest) {
        return
                LoginData
                        .builder()
                        .username(loginRequest.getUsername())
                        .password(loginRequest.getPassword())
                        .build();
    }

    public RegisterData toDataFromRegister(RegisterRequest registerRequest) {
        return RegisterData
                .builder()
                .birthDay(null)
                .password(registerRequest.getPassword())
                .username(registerRequest.getUsername())
                .typeId(userTypeService.getUserTypeByCode(UserTypeCode.ADMIN).getId())
                .build();

    }

    public AddAddressData toDataFromAddAddress(AddAddressRequest addAddressRequest) {
        return AddAddressData
                .builder()
                .type(addAddressRequest.getType())
                .provinceCode(addAddressRequest.getProvinceCode())
                .provinceName(addAddressRequest.getProvinceName())
                .districtCode(addAddressRequest.getDistrictCode())
                .districtName(addAddressRequest.getDistrictName())
                .precinctCode(addAddressRequest.getPrecinctCode())
                .precinctName(addAddressRequest.getPrecinctName())
                .status(addAddressRequest.getStatus())
                .build();
    }

    public AddressData toDataFromEditAddress(EditAddressRequest editAddressRequest) {
        return AddressData
                .builder()
                .id(editAddressRequest.getId())
                .provinceCode(editAddressRequest.getProvinceCode())
                .provinceName(editAddressRequest.getProvinceName())
                .districtCode(editAddressRequest.getDistrictCode())
                .districtName(editAddressRequest.getDistrictName())
                .precinctCode(editAddressRequest.getPrecinctCode())
                .precinctName(editAddressRequest.getPrecinctName())
                .type(editAddressRequest.getType())
                .status(editAddressRequest.getStatus())
                .build();
    }

    public AddRoleData toDataFromAddRole(AddRoleRequest addRoleRequest) {
        return AddRoleData
                .builder()
                .code(addRoleRequest.getCode())
                .name(addRoleRequest.getName())
                .status(addRoleRequest.getStatus())
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
                .status(addUserTypeRequest.getStatus())
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
                    .status(UserStatus.ACTIVED)
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
                    .status(UserStatus.ACTIVED)
                    .star(-1)
                    .userName(addAgencyUserRequest.getUserName())
                    .password(addAgencyUserRequest.getPassword())
                    .name(addAgencyUserRequest.getName())
                    .build();
        }
    }

    public EditUserData toDataFromEditUser(EditUserRequest editAgencyUserRequest) {
        Level levelByIdIfExist = null;
        if (editAgencyUserRequest.getLevel() != null) {
            levelByIdIfExist = levelRepository.findByField("name", editAgencyUserRequest.getLevel());
        }
        Date birthDay;
        try {
            birthDay = DateUtil.parseFromStringFormat(
                    editAgencyUserRequest.getBirthDay(), DateUtil.DATE_DDMMYYYY_PATTERN
            );

        } catch (ParseException e) {
            birthDay = null;
        }
        return EditUserData
                .builder()
                .id(editAgencyUserRequest.getId())
                .companyId(editAgencyUserRequest.getCompanyId())
                .roleId(Objects.isNull(editAgencyUserRequest.getRoleId())
                        ? null : roleRepository.findByField("code", editAgencyUserRequest.getRoleId()).getId())
                .typeId(editAgencyUserRequest.getTypeId())
                .information(editAgencyUserRequest.getInformation())
                .description(editAgencyUserRequest.getDescription())
                .birthDay(birthDay)
                .status(editAgencyUserRequest.getStatus())
                .star(editAgencyUserRequest.getStar())
                .userName(editAgencyUserRequest.getUsername())
                .password(editAgencyUserRequest.getPassword())
                .name(editAgencyUserRequest.getName())
                .cvLink(editAgencyUserRequest.getCvLink())
                .experienceYear(editAgencyUserRequest.getExperienceYear())
                .userTypeCode(editAgencyUserRequest.getUserTypeCode())
                .levelId(levelByIdIfExist != null ? levelByIdIfExist.getId() : null)
                .avatarUrl(editAgencyUserRequest.getAvatarUrl())
                .build();
    }

    public AddUserRoleData toDataFromAddUserRole(AddUserRoleRequest addUserRoleRequest) {
        return AddUserRoleData
                .builder()
                .userId(addUserRoleRequest.getUserId())
                .roleId(addUserRoleRequest.getRoleId())
                .status(EntityStatus.ACTIVED)
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

    public AddStepData toDataFromAddStep(AddStepRequest addStepRequest) {
        return AddStepData
                .builder()
                .stepCode(addStepRequest.getCode())
                .meaning(addStepRequest.getMeaning())
                .status(addStepRequest.getStatus())
                .build();
    }

    public EditStepData toDataFromEditStep(EditStepRequest editStepRequest) {
        return EditStepData
                .builder()
                .id(editStepRequest.getId())
                .code(editStepRequest.getCode())
                .meaning(editStepRequest.getMeaning())
                .status(editStepRequest.getStatus())
                .build();
    }

    public AddProgressData toDataFromAddProgress(AddProgressRequest addProgressRequest) {
        return AddProgressData
                .builder()
                .agencyId(addProgressRequest.getAgencyId())
                .candidateId(addProgressRequest.getCandidateId())
                .stepId(addProgressRequest.getStepId())
                .status(addProgressRequest.getStatus())
                .build();
    }

    public EditProgressData toDataFromEditProgress(EditProgressRequest editProgressRequest) {
        return EditProgressData
                .builder()
                .id(editProgressRequest.getId())
                .agencyId(editProgressRequest.getAgencyId())
                .candidateId(editProgressRequest.getCandidateId())
                .stepId(editProgressRequest.getStepId())
                .status(editProgressRequest.getStatus())
                .build();
    }

    public AddLevelData toDataFromAddLevel(AddLevelRequest addLevelRequest) {
        return AddLevelData
                .builder()
                .levelName(addLevelRequest.getName())
                .meaning(addLevelRequest.getMeaning())
                .status(addLevelRequest.getStatus())
                .build();
    }

    public EditLevelData toDataFromEditLevel(EditLevelRequest editLevelRequest) {
        return EditLevelData
                .builder()
                .levelId(editLevelRequest.getLevelId())
                .levelName(editLevelRequest.getLevelName())
                .meaning(editLevelRequest.getMeaning())
                .status(editLevelRequest.getStatus())
                .build();
    }

    public AddReviewData toDataFromAddReview(AddReviewRequest addReviewRequest) {
        return AddReviewData
                .builder()
                .description(addReviewRequest.getDescription())
                .star(addReviewRequest.getStar())
                .objectId(addReviewRequest.getObjectId())
                .type(addReviewRequest.getType())
                .build();
    }

    public EditReviewData toDataFromEditReview(EditReviewRequest editReviewRequest) {
        return EditReviewData
                .builder()
                .reviewId(editReviewRequest.getReviewId())
                .description(editReviewRequest.getDescription())
                .star(editReviewRequest.getStar())
                .objectId(editReviewRequest.getObjectId())
                .reviewOwner(editReviewRequest.getReviewOwner())
                .type(editReviewRequest.getType())
                .status(editReviewRequest.getStatus())
                .build();
    }

    public AddCompanyData toDataFromAddCompany(AddCompanyRequest addCompanyRequest) {
        return AddCompanyData
                .builder()
                .code(addCompanyRequest.getCode())
                .name(addCompanyRequest.getName())
                .provinceCode(addCompanyRequest.getProvinceCode())
                .districtCode(addCompanyRequest.getDistrictCode())
                .precinctCode(addCompanyRequest.getPrecinctCode())
                .information(addCompanyRequest.getInformation())
                .star(addCompanyRequest.getStar())
                .status(addCompanyRequest.getStatus())
                .build();
    }

    public EditCompanyData toDataFromEditCompany(EditCompanyRequest editCompanyRequest) {
        return EditCompanyData
                .builder()
                .id(editCompanyRequest.getCompanyId())
                .code(editCompanyRequest.getCode())
                .name(editCompanyRequest.getName())
                .provinceCode(editCompanyRequest.getProvinceCode())
                .districtCode(editCompanyRequest.getDistrictCode())
                .precinctCode(editCompanyRequest.getPrecinctCode())
                .information(editCompanyRequest.getInformation())
                .star(editCompanyRequest.getStar())
                .status(editCompanyRequest.getStatus())
                .build();
    }

    public AddMenuItemData toDataFromAddMenuItem(AddMenuItemRequest addMenuItemRequest) {
        return AddMenuItemData
                .builder()
                .name(addMenuItemRequest.getName())
                .parentId(addMenuItemRequest.getParentId())
                .path(addMenuItemRequest.getPath())
                .pathAddressPhysic(addMenuItemRequest.getPathAddressPhysic())
                .status(addMenuItemRequest.getStatus())
                .type(addMenuItemRequest.getType())
                .build();
    }

    public EditMenuItemData toDataFromEditMenuItem(EditMenuItemRequest editMenuItemData) {
        return EditMenuItemData
                .builder()
                .id(editMenuItemData.getId())
                .name(editMenuItemData.getName())
                .parentId(editMenuItemData.getParentId())
                .path(editMenuItemData.getPath())
                .pathAddressPhysic(editMenuItemData.getPathAddressPhysic())
                .status(editMenuItemData.getStatus())
                .type(editMenuItemData.getType())
                .build();
    }

    public AddMenuUserData toDataFromAddMenuUser(AddMenuUserRequest addMenuUserRequest) {
        return AddMenuUserData
                .builder()
                .userId(addMenuUserRequest.getUserId())
                .menuId(addMenuUserRequest.getMenuId())
                .build();
    }

    public EditMenuUserData toDataFromEditMenuUser(EditMenuUserRequest editMenuUserRequest) {
        return EditMenuUserData
                .builder()
                .id(editMenuUserRequest.getId())
                .userId(editMenuUserRequest.getUserId())
                .menuId(editMenuUserRequest.getMenuId())
                .build();
    }

    public SearchCompanyData toDataFromSearchCompany(
            String companyCode,
            String companyName,
            String provinceCode,
            String districtCode,
            String precinctCode,
            String information,
            EntityStatus status,
            int star,
            int page,
            int size
    ) {
        return SearchCompanyData
                .builder()
                .companyCode(EzyStrings.isBlank(companyCode) ? null : companyCode)
                .companyName(EzyStrings.isBlank(companyName) ? null : companyName)
                .districtCode(EzyStrings.isBlank(districtCode) ? null : districtCode)
                .provinceCode(EzyStrings.isBlank(provinceCode) ? null : provinceCode)
                .precinctCode(EzyStrings.isBlank(precinctCode) ? null : precinctCode)
                .status(status)
                .information(information.equalsIgnoreCase("") ? null : information)
                .star(star)
                .page(page)
                .size(size)
                .build();
    }

    public SearchAddressData toDataFromSearchAddress(
            String provinceCode,
            String provinceName,
            String districtCode,
            String districtName,
            String precinctCode,
            String precinctName,
            AddressType type,
            EntityStatus status,
            int page,
            int size
    ) {
        return SearchAddressData
                .builder()
                .provinceCode(EzyStrings.isBlank(provinceCode) ? null : provinceCode)
                .provinceName(EzyStrings.isBlank(provinceName) ? null : provinceName)
                .districtCode(EzyStrings.isBlank(districtCode) ? null : districtCode)
                .districtName(EzyStrings.isBlank(districtName) ? null : districtName)
                .precinctCode(EzyStrings.isBlank(precinctCode) ? null : precinctCode)
                .precinctName(EzyStrings.isBlank(precinctName) ? null : precinctName)
                .type(type)
                .status(status)
                .page(page)
                .size(size)
                .build();
    }

    public UserRegisterData toDataFromUserRegister(UserRegisterRequest userRegisterRequest) {
        return UserRegisterData
                .builder()
                .birthDay(null)
                .password(userRegisterRequest.getPassword())
                .username(userRegisterRequest.getUsername())
                .fullname(userRegisterRequest.getFullname())
                .typeId(userTypeService.getUserTypeByCode(UserTypeCode.AGENCY).getId())
                .build();
    }

    public SearchUserData toDataFromSearchUser(int page,
                                               int size,
                                               Long typeId,
                                               String name,
                                               String username,
                                               Long companyId,
                                               UserTypeCode userType,
                                               LevelName level,
                                               Integer experience,
                                               Integer star,
                                               EntityStatus status) {
        int skip = size * page;
        return SearchUserData
                .builder()
                .size(size)
                .skip(skip)
                .name(name)
                .username(username)
                .companyId(companyId)
                .level(level != null ? level.toString() : null)
                .experience(experience)
                .star(star)
                .userType(userType != null ? userType.toString() : null)
                .status(status)
                .typeId(typeId)
                .build();
    }
}
