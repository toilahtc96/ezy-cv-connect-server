package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.*;
import com.ezyfox.cvconnect.model.AddJobTypeData;
import com.ezyfox.cvconnect.response.JobTypeResponse;

import java.util.List;
import java.util.Map;

public interface JobTypeService {

    void addJobType(AddJobTypeData jobTypeData);

    void editJobType(JobTypeData jobTypeData);

    List<JobTypeResponse> getJobTypeByCodeActive(String code);

    List<JobTypeResponse> getJobTypeByNameActive(String name);

    List<JobTypeResponse> getAllJobTypeActive();

    List<JobTypeResponse> getAllJobType();

    List<JobTypeResponse> getPaging(int page, int size);

    JobTypeResponse getById(long id);

    Map<String, Object> getByField(SearchJobTypeData searchJobTypeData);
}
