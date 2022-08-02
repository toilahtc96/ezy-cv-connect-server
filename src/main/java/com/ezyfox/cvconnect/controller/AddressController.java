package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;
import com.ezyfox.cvconnect.request.AddAddressRequest;
import com.ezyfox.cvconnect.request.EditAddressRequest;
import com.ezyfox.cvconnect.response.AddressResponse;
import com.ezyfox.cvconnect.service.AddressService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

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

    @DoPost("/edit")
    public ResponseEntity editAddress(@RequestBody EditAddressRequest editAddressRequest) {
        AddressData addressData = requestToDataConverter.toAddressDataFromEdit(editAddressRequest);
        addressService.editAddress(addressData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-type")
    public ResponseEntity getByType(@RequestParam int type) {
        List<AddressResponse> listByType = addressService.getAddressByType(type);
        return ResponseEntity.ok(listByType);
    }

    @DoGet("/get-by-parentId")
    public ResponseEntity getByParentId(@RequestParam long parentId) {
        List<AddressResponse> listByParentId = addressService.getAddressByParentId(parentId);
        return ResponseEntity.ok(listByParentId);
    }

    @DoGet("/get-by-type-and-parentId")
    public ResponseEntity getByTypeAndParentId(
        @RequestParam("type") int type,
        @RequestParam("parentId") long parentId
    ) {
        List<AddressResponse> listByTypeAndParentId = addressService
            .getAddressByTypeAndParentId(type, parentId);
        return ResponseEntity.ok(listByTypeAndParentId);
    }
}
