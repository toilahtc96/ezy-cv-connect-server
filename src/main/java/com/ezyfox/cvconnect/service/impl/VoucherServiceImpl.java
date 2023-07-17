package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.annotation.UserId;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Voucher;
import com.ezyfox.cvconnect.model.AddVoucherData;
import com.ezyfox.cvconnect.model.EditVoucherData;
import com.ezyfox.cvconnect.model.SearchVoucherData;
import com.ezyfox.cvconnect.repository.VoucherRepository;
import com.ezyfox.cvconnect.response.VoucherResponse;
import com.ezyfox.cvconnect.service.VoucherService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@EzySingleton
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToResponseConverter entityToResponseConverter;

    @Override
    public void addVoucher(AddVoucherData addVoucherData, @UserId Long userId) {
        voucherRepository.save(dataToEntityConverter.dataToVoucher(addVoucherData, userId));
    }

    @Override
    public void editVoucher(EditVoucherData editVoucherData, @UserId Long userId) {
        voucherRepository.save(dataToEntityConverter.editDataToVoucher(editVoucherData, userId));
    }

    @Override
    public Map<String, Object> getByField(SearchVoucherData searchVoucherData) {
        Map<String, Object> result = new HashMap<>();
        int skip = searchVoucherData.getPage() * searchVoucherData.getSize();
        List<Voucher> listVoucher = voucherRepository.findByField(
                searchVoucherData.getVoucherType() == null ?  null : searchVoucherData.getVoucherType().toString(),
            searchVoucherData.getValue(),
            searchVoucherData.getStatus() == null ?  null : searchVoucherData.getStatus().toString(),
            searchVoucherData.getCreatedId(),
            searchVoucherData.getFrom(),
            searchVoucherData.getTo(),
            searchVoucherData.getTitle(),
            searchVoucherData.getSize(),
            skip
        );
        BigInteger totalListVoucherByField = voucherRepository.totalByField(
            searchVoucherData.getVoucherType() == null ?  null : searchVoucherData.getVoucherType().toString(),
            searchVoucherData.getValue(),
            searchVoucherData.getStatus() == null ?  null : searchVoucherData.getStatus().toString(),
            searchVoucherData.getCreatedId(),
            searchVoucherData.getFrom(),
            searchVoucherData.getTo(),
            searchVoucherData.getTitle()
        );
        result.put("data", listVoucher);
        result.put("total", totalListVoucherByField);
        return result;
    }

    @Override
    public VoucherResponse getById(long id) {
        return entityToResponseConverter.toVoucherResponse(voucherRepository.findById(id));
    }
}
