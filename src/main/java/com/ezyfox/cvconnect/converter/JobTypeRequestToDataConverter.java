package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.model.AddJobTypeData;
import com.ezyfox.cvconnect.model.JobTypeData;
import com.ezyfox.cvconnect.model.SearchJobTypeData;
import com.ezyfox.cvconnect.request.AddJobTypeRequest;
import com.ezyfox.cvconnect.request.JobTypeRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class JobTypeRequestToDataConverter {

    public JobTypeData toDataFromEditJobType(JobTypeRequest editJobTypeRequest) {
        return JobTypeData
                .builder()
                .id(editJobTypeRequest.getId())
                .name(editJobTypeRequest.getName())
                .description(editJobTypeRequest.getDescription())
                .status(editJobTypeRequest.getStatus())
                .build();
    }

    public AddJobTypeData toDataFromAddJobType(AddJobTypeRequest addJobTypeRequest) {
        return AddJobTypeData
                .builder()
                .name(addJobTypeRequest.getName())
                .description(addJobTypeRequest.getDescription())
                .status(addJobTypeRequest.getStatus())
                .build();
    }

    public SearchJobTypeData toDataFromSearchJobType(String name, int page, int size) {
        return SearchJobTypeData.builder().name(name).page(page).size(size).build();
    }
}
