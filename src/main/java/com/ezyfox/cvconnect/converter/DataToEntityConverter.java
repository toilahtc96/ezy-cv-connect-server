package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.UserStatus;
import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.entity.Step;
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
                .provinceCode(addAddressData.getProvinceCode())
                .provinceName(addAddressData.getProvinceName())
                .districtCode(addAddressData.getDistrictCode())
                .districtName(addAddressData.getDistrictName())
                .precinctCode(addAddressData.getPrecinctCode())
                .precinctName(addAddressData.getPrecinctName())
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

    public Step dataToStep(AddStepData addStepData) {
        return Step.builder()
                .code(addStepData.getStepCode())
                .meaning(addStepData.getMeaning())
                .status(addStepData.getStatus())
                .order(addStepData.getOrder())
                .build();
    }

    public Progress dataToProgress(AddProgressData addProgressData) {
        LocalDateTime now = LocalDateTime.now();
        return Progress.builder()
                .agencyId(addProgressData.getAgencyId())
                .candidateId(addProgressData.getCandidateId())
                .stepId(addProgressData.getStepId())
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

    public Review dataToReview(AddReviewData addReviewData, long userId) {
        LocalDateTime now = LocalDateTime.now();
        return Review.builder()
                .description(addReviewData.getDescription())
                .star(addReviewData.getStar())
                .objectId(addReviewData.getObjectId())
                .type(addReviewData.getType())
                .status(EntityStatus.ACTIVED)
                .createdTime(now)
                .createdId(userId)
                .reviewOwner(userId)
                .build();
    }

    public Company dataToCompany(AddCompanyData addCompanyData) {
        LocalDateTime now = LocalDateTime.now();
        return Company.builder()
                .code(
                    addCompanyData.getName()
                        .concat(addCompanyData.getProvinceCode())
                        .concat(addCompanyData.getDistrictCode())
                        .concat(addCompanyData.getPrecinctCode())
                )
                .provinceCode(addCompanyData.getProvinceCode())
                .districtCode(addCompanyData.getDistrictCode())
                .precinctCode(addCompanyData.getPrecinctCode())
                .information(addCompanyData.getInformation())
                .name(addCompanyData.getName())
                .star(addCompanyData.getStar())
                .status(EntityStatus.ACTIVED)
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

    public WorkingForm dataToWorkingForm(AddWorkingFormData addWorkingFormData) {
        return WorkingForm.builder()
                .name(addWorkingFormData.getName())
                .status(addWorkingFormData.getStatus())
                .description(addWorkingFormData.getDescription())
                .createdTime(LocalDateTime.now())
                .build();
    }

    public Job dataToJob(AddJobData addJobData, Long userId) {
        return Job
                .builder()
                .jobTypeId(addJobData.getJobTypeId())
                .companyId(addJobData.getCompanyId())
                .levelId(addJobData.getLevelId())
                .careerId(addJobData.getCareerId())
                .workingFormId(addJobData.getWorkingFormId())
                .quantity(addJobData.getQuantity())
                .rangeSalaryMin(addJobData.getRangeSalaryMin())
                .rangeSalaryMax(addJobData.getRangeSalaryMax())
                .information(addJobData.getInformation())
                .status(addJobData.getStatus() != null ? addJobData.getStatus() : EntityStatus.ACTIVED)
                .customRange(addJobData.getCustomRange())
                .createdTime(LocalDateTime.now())
                .createdId(userId)
                .thumbnail(addJobData.getThumbnail())
                .title(addJobData.getTitle())
                .voucherId(addJobData.getVoucherId())
                .tags(addJobData.getTags())
                .reasonForChoosing(addJobData.getReasonForChoosing())
                .build();
    }

    public Job editDataToJob(EditJobData editJobData) {
        return Job
                .builder()
                .id(editJobData.getId())
                .jobTypeId(editJobData.getJobTypeId())
                .companyId(editJobData.getCompanyId())
                .levelId(editJobData.getLevelId())
                .careerId(editJobData.getCareerId())
                .workingFormId(editJobData.getWorkingFormId())
                .quantity(editJobData.getQuantity())
                .rangeSalaryMin(editJobData.getRangeSalaryMin())
                .rangeSalaryMax(editJobData.getRangeSalaryMax())
                .information(editJobData.getInformation())
                .status(editJobData.getStatus())
                .customRange(editJobData.getCustomRange())
                .updatedTime(LocalDateTime.now())
                .thumbnail(editJobData.getThumbnail())
                .title(editJobData.getTitle())
                .voucherId(editJobData.getVoucherId())
                .tags(editJobData.getTags())
                .reasonForChoosing(editJobData.getReasonForChoosing())
                .build();
    }

    public Voucher dataToVoucher(AddVoucherData addVoucherData, Long userId) {
        LocalDateTime now = LocalDateTime.now();
        return Voucher
                .builder()
                .title(addVoucherData.getTitle())
                .value(addVoucherData.getValue())
                .startTime(addVoucherData.getStartTime())
                .endTime(addVoucherData.getEndTime())
                .voucherType(addVoucherData.getVoucherType())
                .status(addVoucherData.getStatus())
                .createdId(userId)
                .createdTime(now)
                .build();
    }

    public Voucher editDataToVoucher(EditVoucherData editVoucherData, Long userId) {
        LocalDateTime now = LocalDateTime.now();
        return Voucher
                .builder()
                .id(editVoucherData.getId())
                .title(editVoucherData.getTitle())
                .value(editVoucherData.getValue())
                .startTime(editVoucherData.getStartTime())
                .endTime(editVoucherData.getEndTime())
                .voucherType(editVoucherData.getVoucherType())
                .status(editVoucherData.getStatus())
                .createdId(userId)
                .updatedTime(now)
                .build();
    }
}
