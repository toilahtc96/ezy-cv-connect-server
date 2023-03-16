package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.model.AddCareerData;
import com.ezyfox.cvconnect.model.CareerData;
import com.ezyfox.cvconnect.request.AddCareerRequest;
import com.ezyfox.cvconnect.request.CareerRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class CareerRequestToDataConverter {

    public CareerData toDataFromEditCareer(CareerRequest editCareerRequest) {
        return CareerData
                .builder()
                .id(editCareerRequest.getId())
                .name(editCareerRequest.getName())
                .status(editCareerRequest.getStatus())
                .build();
    }

    public AddCareerData toDataFromAddCareer(AddCareerRequest addCareerRequest) {
        return AddCareerData
                .builder()
                .name(addCareerRequest.getName())
                .status(addCareerRequest.getStatus())
                .build();
    }
}
