package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;
import com.ezyfox.cvconnect.response.AddressResponse;

import java.util.List;

public interface AddressService {

    void saveAddress(AddAddressData data);

    void editAddress(AddressData data);

    List<AddressResponse> getAddressByType(int type);

    List<AddressResponse> getAddressByParentId(long parentId);

    List<AddressResponse> getAddressByTypeAndParentId(int type, long parentId);

}
