package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.entity.Process;
import com.ezyfox.cvconnect.entity.*;
import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.service.AuthenticationService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.security.EzySHA256;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@EzySingleton
@AllArgsConstructor
public class DataToEntityConverter {

    private final AuthenticationService authenticationService;

    public User dataToUserAdmin(RegisterData registerData) {
        return User.builder()
            .birthDay(registerData.getBirthDay())
            .name(registerData.getName())
            .username(registerData.getUsername())
            .password(EzySHA256.cryptUtfToLowercase(registerData.getPassword()))
            .typeId(registerData.getTypeId())
            .status(UserStatus.ACTIVED)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public Address dataToAddress(AddAddressData addAddressData) {
        return Address.builder()
            .type(addAddressData.getType())
            .name(addAddressData.getName())
            .parentId(addAddressData.getParentId())
            .status(EntityStatus.ACTIVED)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public Role dataToRole(AddRoleData addRoleData) {
        return Role.builder()
            .name(addRoleData.getName())
            .code(addRoleData.getCode())
            .status(addRoleData.getStatus())
            .createdTime(LocalDateTime.now())
            .build();
    }

    public UserType dataToUserType(AddUserTypeData addUserTypeData) {
        return UserType
            .builder()
            .code(UserTypeCode.valueOf(addUserTypeData.getCode()))
            .meaning(addUserTypeData.getMeaning())
            .status(addUserTypeData.getStatus())
            .createdTime(LocalDateTime.now())
            .build();
    }

    public User dataToAgencyUser(AddAgencyData addAgencyData) {
        return User
            .builder()
            .birthDay(addAgencyData.getBirthDay())
            .companyId(addAgencyData.getCompanyId())
            .status(addAgencyData.getStatus())
            .name(addAgencyData.getName())
            .information(addAgencyData.getInformation())
            .description(addAgencyData.getDescription())
            .username(addAgencyData.getUserName())
            .password(EzySHA256.cryptUtfToLowercase(addAgencyData.getPassword()))
            .roleId(addAgencyData.getRoleId())
            .typeId(addAgencyData.getTypeId())
            .star(addAgencyData.getStar())
            .createdTime(LocalDateTime.now())
            .build();
    }

    public UserRole dataToUserRole(AddUserRoleData userRoleData) {
        return UserRole.builder()
            .userId(userRoleData.getUserId())
            .roleId(userRoleData.getRoleId())
            .status(EntityStatus.ACTIVED)
            .build();
    }

    public Process dataToProcess(AddProcessData addProcessData) {
        return Process.builder()
            .code(addProcessData.getProcessCode())
            .meaning(addProcessData.getMeaning())
            .status(addProcessData.getStatus())
            .build();
    }

    public Deal dataToDeal(AddDealData addDealData) {
        LocalDateTime now = LocalDateTime.now();
        return Deal.builder()
            .agencyId(addDealData.getAgencyId())
            .candidateId(addDealData.getCandidateId())
            .processId(addDealData.getProcessId())
            .status(EntityStatus.ACTIVED)
            .createdTime(now)
            .build();
    }

    public Level dataToLevel(AddLevelData addLevelData) {
        LocalDateTime now = LocalDateTime.now();
        return Level.builder()
            .name(addLevelData.getLevelName())
            .meaning(addLevelData.getMeaning())
            .status(EntityStatus.ACTIVED)
            .createdTime(now)
            .build();
    }

    public Review dataToReview(AddReviewData addReviewData) {
        LocalDateTime now = LocalDateTime.now();
        return Review.builder()
            .description(addReviewData.getDescription())
            .reviewOwner(addReviewData.getReviewOwner())
            .star(addReviewData.getStar())
            .objectId(addReviewData.getObjectId())
            .type(addReviewData.getType())
            .status(EntityStatus.ACTIVED)
            .createdTime(now)
            .build();
    }

    public Company dataToCompany(AddCompanyData addCompanyData) {
        LocalDateTime now = LocalDateTime.now();
        return Company.builder()
            .code(addCompanyData.getCode())
            .provinceCode(addCompanyData.getProvinceCode())
            .districtCode(addCompanyData.getDistrictCode())
            .precinctCode(addCompanyData.getPrecinctCode())
            .information(addCompanyData.getInformation())
            .name(addCompanyData.getName())
            .star(addCompanyData.getStar())
            .status(EntityStatus.ACTIVED.name())
            .createdTime(now)
            .build();
    }

    public MenuItem dataToMenuItem(AddMenuItemData addMenuItemData) {
        LocalDateTime now = LocalDateTime.now();
        return MenuItem
            .builder()
            .name(addMenuItemData.getName())
            .createdTime(now)
            .parentId(addMenuItemData.getParentId())
            .type(addMenuItemData.getType())
            .status(addMenuItemData.getStatus())
            .path(addMenuItemData.getPath())
            .pathAddressPhysic(addMenuItemData.getPathAddressPhysic())
            .build();
    }

    public MenuUser dataToMenuUser(AddMenuUserData addMenuUserData) {
        return MenuUser
            .builder()
            .menuId(addMenuUserData.getMenuId())
            .userId(addMenuUserData.getUserId())
            .build();
    }

    public User dataToUser(UserRegisterData userRegisterData) {
        return User.builder()
            .birthDay(userRegisterData.getBirthDay())
            .name(userRegisterData.getName())
            .username(userRegisterData.getUsername())
            .password(EzySHA256.cryptUtfToLowercase(userRegisterData.getPassword()))
            .typeId(userRegisterData.getTypeId())
            .status(UserStatus.ACTIVED)
            .createdTime(LocalDateTime.now())
            .build();
    }

    public Career dataToCareer(AddCareerData addCareerData) {
        return Career.builder()
            .name(addCareerData.getName())
            .status(addCareerData.getStatus())
            .createdTime(LocalDateTime.now())
            .build();
    }

    public JobType dataToJobType(AddJobTypeData addJobTypeData) {
        return JobType.builder()
                .name(addJobTypeData.getName())
                .status(addJobTypeData.getStatus())
                .description(addJobTypeData.getDescription())
                .createdTime(LocalDateTime.now())
                .build();
    }
}
