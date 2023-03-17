package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.model.AddWorkingFormData;
import com.ezyfox.cvconnect.model.SearchWorkingFormData;
import com.ezyfox.cvconnect.model.WorkingFormData;
import com.ezyfox.cvconnect.request.AddWorkingFormRequest;
import com.ezyfox.cvconnect.request.WorkingFormRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class WorkingFormRequestToDataConverter {

    public WorkingFormData toDataFromEditWorkingForm(WorkingFormRequest editWorkingFormRequest) {
        return WorkingFormData
                .builder()
                .id(editWorkingFormRequest.getId())
                .name(editWorkingFormRequest.getName())
                .description(editWorkingFormRequest.getDescription())
                .status(editWorkingFormRequest.getStatus())
                .build();
    }

    public AddWorkingFormData toDataFromAddWorkingForm(AddWorkingFormRequest addWorkingFormRequest) {
        return AddWorkingFormData
                .builder()
                .name(addWorkingFormRequest.getName())
                .description(addWorkingFormRequest.getDescription())
                .status(addWorkingFormRequest.getStatus())
                .build();
    }

    public SearchWorkingFormData toDataFromSearchWorkingForm(String name, int page, int size) {
        return SearchWorkingFormData.builder().name(name).page(page).size(size).build();
    }
}
