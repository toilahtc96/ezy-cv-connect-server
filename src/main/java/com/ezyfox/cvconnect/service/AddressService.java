package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;

import java.util.List;

public interface AddressService {
    AddressData saveAddress(AddAddressData data);
    AddressData getAddressById(long id);
    List<AddressData> getAddressByType(int type);
    List<AddressData> getAddressByParentId(long parentId);
    int countAddressByParentId(long parentId);
}
