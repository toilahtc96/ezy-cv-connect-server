package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddVoucherData;
import com.ezyfox.cvconnect.model.EditVoucherData;
import com.ezyfox.cvconnect.model.SearchVoucherData;
import com.ezyfox.cvconnect.response.VoucherResponse;

import java.util.Map;

public interface VoucherService {
    void addVoucher(AddVoucherData addVoucherData, Long userId);

    void editVoucher(EditVoucherData editVoucherData, Long userId);

    Map<String, Object> getByField(SearchVoucherData searchVoucherData);

    VoucherResponse getById(long id);
}
