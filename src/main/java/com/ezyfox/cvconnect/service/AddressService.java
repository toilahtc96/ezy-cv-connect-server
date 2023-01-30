package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;
import com.ezyfox.cvconnect.response.AddressResponse;

import java.util.List;

public interface AddressService {

    void saveAddress(AddAddressData data);

    void editAddress(AddressData data);

    List<AddressResponse> getAddressByType(AddressType type);

    List<AddressResponse> getAddressByParentId(long parentId);

    List<AddressResponse> getAddressByTypeAndParentId(AddressType type, long parentId);

    List<AddressResponse> getAll();

    AddressResponse getById(long id);

    List<AddressResponse> getByParentCode(String code);

}
