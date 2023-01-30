package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.AddressType;
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
        AddAddressData addAddressData = requestToDataConverter.toDataFromAddAddress(addAddressRequest);
        addressService.saveAddress(addAddressData);
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editAddress(@RequestBody EditAddressRequest editAddressRequest) {
        AddressData addressData = requestToDataConverter.toDataFromEditAddress(editAddressRequest);
        addressService.editAddress(addressData);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-type")
    public List<AddressResponse> getByType(@RequestParam("type") AddressType type) {
        return addressService.getAddressByType(type);
    }

    @DoGet("/get-by-parentId")
    public List<AddressResponse> getByParentId(@RequestParam long parentId) {
        return addressService.getAddressByParentId(parentId);
    }

    @DoGet("/get-by-type-and-parentId")
    public List<AddressResponse> getByTypeAndParentId(
        @RequestParam("type") AddressType type,
        @RequestParam("parentId") long parentId
    ) {
        return addressService.getAddressByTypeAndParentId(type, parentId);
    }

    @DoGet("/get-all")
    public List<AddressResponse> getAll() {
        return addressService.getAll();
    }

    @DoGet("/get-by-id")
    public AddressResponse getById(@RequestParam long id) {
        return addressService.getById(id);
    }

    @DoGet("/get-by-parent-code")
    public List<AddressResponse> getByCode(@RequestParam String code) {
        return addressService.getByParentCode(code);
    }
}
