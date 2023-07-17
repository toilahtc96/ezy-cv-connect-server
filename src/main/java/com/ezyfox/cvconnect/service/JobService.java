package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddJobData;
import com.ezyfox.cvconnect.model.EditJobData;
import com.ezyfox.cvconnect.model.SearchJobData;
import com.ezyfox.cvconnect.response.JobResponse;

import java.util.Map;

public interface JobService {

    void addJob(AddJobData addJobData, Long userId);

    void editJob(EditJobData editJobData);

    JobResponse getById(long id);

    Map<String, Object> getByField(SearchJobData searchJobData);
}
