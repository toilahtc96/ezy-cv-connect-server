package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.request.AddAddressRequest;
import com.ezyfox.cvconnect.service.AddressService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Authenticated;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import lombok.AllArgsConstructor;

@Controller("api/v1/address")
@AllArgsConstructor
@Authenticated
public class AddressController {
    private final AddressService addressService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addAddress(@RequestBody AddAddressRequest addAddressRequest) {
        AddAddressData addAddressData = requestToDataConverter.toAddAddressData(addAddressRequest);
        addressService.saveAddress(addAddressData);
        return ResponseEntity.noContent();
    }
}
