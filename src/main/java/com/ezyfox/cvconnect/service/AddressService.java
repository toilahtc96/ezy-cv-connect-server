package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.AddressType;
import com.ezyfox.cvconnect.model.AddAddressData;
import com.ezyfox.cvconnect.model.AddressData;
import com.ezyfox.cvconnect.model.SearchAddressData;
import com.ezyfox.cvconnect.model.SearchCompanyData;
import com.ezyfox.cvconnect.response.AddressResponse;

import java.util.List;
import java.util.Map;

public interface AddressService {

    void saveAddress(AddAddressData data) throws IllegalAccessException;

    void editAddress(AddressData data);

    List<AddressResponse> getAddressByType(AddressType type);

    List<AddressResponse> getAddressByParentId(long parentId);

    List<AddressResponse> getAddressByTypeAndParentId(AddressType type, long parentId);

    List<AddressResponse> getAll();

    AddressResponse getById(long id);

    List<AddressResponse> getByParentCode(String code);

    AddressResponse getByCodeAndType(String code, AddressType type);

    List<AddressResponse> getAddressByTypePaging(AddressType type, int offset, int size);

    Map<String, Object> getByField(SearchAddressData searchAddressData);
}
