package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.LevelName;
import com.ezyfox.cvconnect.entity.Level;
import com.ezyfox.cvconnect.model.AddCareerData;
import com.ezyfox.cvconnect.model.AddJobData;
import com.ezyfox.cvconnect.model.EditJobData;
import com.ezyfox.cvconnect.model.SearchJobData;
import com.ezyfox.cvconnect.repository.LevelRepository;
import com.ezyfox.cvconnect.request.AddJobRequest;
import com.ezyfox.cvconnect.request.EditJobRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class JobRequestToDataConverter {

    private final LevelRepository levelRepository;

    public EditJobData toDataFromEditJob(EditJobRequest editJobRequest) {
        Long levelId = null;
        if (editJobRequest.getLevelName() != null) {
            Level level = levelRepository.getLevelByName(editJobRequest.getLevelName());
            if (level != null) {
                levelId = level.getId();
            }
        }
        return EditJobData
            .builder()
            .id(editJobRequest.getId())
            .jobTypeId(editJobRequest.getJobTypeId())
            .companyId(editJobRequest.getCompanyId())
            .levelId(levelId)
            .careerId(editJobRequest.getCareerId())
            .workingFormId(editJobRequest.getWorkingFormId())
            .quantity(editJobRequest.getQuantity())
            .rangeSalaryMin(editJobRequest.getRangeSalaryMin())
            .rangeSalaryMax(editJobRequest.getRangeSalaryMax())
            .information(editJobRequest.getInformation())
            .status(editJobRequest.getStatus())
            .customRange(editJobRequest.getCustomRange())
            .build();
    }

    public AddJobData toDataFromAddJob(AddJobRequest addJobRequest) {
        Long levelId = null;
        if (addJobRequest.getLevelName() != null) {
            Level level = levelRepository.getLevelByName(addJobRequest.getLevelName());
            if (level != null) {
                levelId = level.getId();
            }
        }
        return AddJobData
            .builder()
            .jobTypeId(addJobRequest.getJobTypeId())
            .companyId(addJobRequest.getCompanyId())
            .levelId(levelId)
            .careerId(addJobRequest.getCareerId())
            .workingFormId(addJobRequest.getWorkingFormId())
            .quantity(addJobRequest.getQuantity())
            .rangeSalaryMin(addJobRequest.getRangeSalaryMin())
            .rangeSalaryMax(addJobRequest.getRangeSalaryMax())
            .information(addJobRequest.getInformation())
            .status(addJobRequest.getStatus())
            .customRange(addJobRequest.getCustomRange())
            .build();
    }

    public SearchJobData toDataFromSearchJob(
        Long jobTypeId,
        Long companyId,
        Double rangeSalary,
        LevelName levelName,
        Boolean customRange,
        Long careerId,
        Long workingFormId,
        String information,
        EntityStatus status,
        int page,
        int size
    ) {
        Long levelId = null;
        if (levelName != null) {
            Level level = levelRepository.getLevelByName(levelName);
            if (level != null) {
                levelId = level.getId();
            }
        }
        String statusStr = status == null ? null : status.toString();
        return SearchJobData
            .builder()
            .jobTypeId(jobTypeId)
            .companyId(companyId)
            .rangeSalary(rangeSalary)
            .levelId(levelId)
            .customRange(customRange)
            .careerId(careerId)
            .workingFormId(workingFormId)
            .information(information)
            .page(page)
            .size(size)
            .status(statusStr)
            .build();
    }
}
