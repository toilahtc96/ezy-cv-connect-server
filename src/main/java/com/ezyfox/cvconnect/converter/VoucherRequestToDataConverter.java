package com.ezyfox.cvconnect.converter;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import com.ezyfox.cvconnect.model.AddVoucherData;
import com.ezyfox.cvconnect.model.EditVoucherData;
import com.ezyfox.cvconnect.model.SearchVoucherData;
import com.ezyfox.cvconnect.request.AddVoucherRequest;
import com.ezyfox.cvconnect.request.EditVoucherRequest;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@EzySingleton
@AllArgsConstructor
public class VoucherRequestToDataConverter {

    public EditVoucherData toDataFromEditVoucher(EditVoucherRequest editVoucherRequest) {
        return EditVoucherData
            .builder()
            .id(editVoucherRequest.getId())
            .title(editVoucherRequest.getTitle())
            .value(editVoucherRequest.getValue())
            .startTime(editVoucherRequest.getStartTime())
            .endTime(editVoucherRequest.getEndTime())
            .voucherType(editVoucherRequest.getVoucherType())
            .status(editVoucherRequest.getStatus())
            .build();
    }

    public AddVoucherData toDataFromAddVoucher(AddVoucherRequest addVoucherRequest) {
        return AddVoucherData
            .builder()
            .title(addVoucherRequest.getTitle())
            .value(addVoucherRequest.getValue())
            .startTime(addVoucherRequest.getStartTime())
            .endTime(addVoucherRequest.getEndTime())
            .voucherType(addVoucherRequest.getVoucherType())
            .status(addVoucherRequest.getStatus())
            .build();
    }

    public SearchVoucherData toDataFromSearchVoucher(
        String title,
        VoucherType voucherType,
        String value,
        LocalDateTime from,
        LocalDateTime to,
        EntityStatus status,
        int page,
        int size
    ) {
        return SearchVoucherData
            .builder()
            .page(page)
            .size(size)
            .title(title)
            .value(value)
            .voucherType(voucherType)
            .from(from)
            .to(to)
            .status(status)
            .build();
    }
}
