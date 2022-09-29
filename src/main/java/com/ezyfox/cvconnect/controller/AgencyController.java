package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.UserTypeCode;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.entity.UserType;
import com.ezyfox.cvconnect.model.AddAgencyData;
import com.ezyfox.cvconnect.repository.UserTypeRepository;
import com.ezyfox.cvconnect.request.AddAgencyUserRequest;
import com.ezyfox.cvconnect.service.UserAgencyService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import lombok.AllArgsConstructor;

@Controller("api/v1/user")
@AllArgsConstructor
public class AgencyController {

    private final UserAgencyService userAgencyService;
    private final RequestToDataConverter requestToDataConverter;
    private final UserTypeRepository userTypeRepository;

    @DoPost("/add-agency")
    public ResponseEntity addAgency(@RequestBody AddAgencyUserRequest addAgencyUserRequest) {
        UserType userTypeAgency = userTypeRepository.getUserTypeByCode(UserTypeCode.AGENCY);
        AddAgencyData userAgency = requestToDataConverter.toDataFromAddAgency(addAgencyUserRequest);
        if (userTypeAgency == null) {
            return ResponseEntity.notFound("can't create agency ! Not found User Type Agency");
        }
        userAgency.setTypeId(userTypeAgency.getId());
        userAgencyService.registerAgencyUser(userAgency);
        return ResponseEntity.noContent();
    }

}
