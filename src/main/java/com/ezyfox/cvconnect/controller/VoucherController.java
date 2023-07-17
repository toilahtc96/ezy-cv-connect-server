package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.annotation.UserId;
import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.constant.VoucherType;
import com.ezyfox.cvconnect.converter.VoucherRequestToDataConverter;
import com.ezyfox.cvconnect.model.SearchVoucherData;
import com.ezyfox.cvconnect.request.AddVoucherRequest;
import com.ezyfox.cvconnect.request.EditVoucherRequest;
import com.ezyfox.cvconnect.response.VoucherResponse;
import com.ezyfox.cvconnect.service.VoucherService;
import com.ezyfox.cvconnect.util.DateUtil;
import com.sun.istack.Nullable;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.text.ParseException;
import java.util.Map;

@Controller("api/v1/voucher")
@AllArgsConstructor
@Authenticated
public class VoucherController {
    private final VoucherService voucherService;
    private final VoucherRequestToDataConverter voucherRequestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addVoucher(@RequestBody AddVoucherRequest addVoucherRequest, @UserId Long userId) {
        voucherService.addVoucher(voucherRequestToDataConverter.toDataFromAddVoucher(addVoucherRequest), userId);
        return ResponseEntity.noContent();
    }


    @DoPost("/edit")
    public ResponseEntity editVoucher(@RequestBody EditVoucherRequest editVoucherRequest, @UserId Long userId) {
        voucherService.editVoucher(voucherRequestToDataConverter.toDataFromEditVoucher(editVoucherRequest), userId);
        return ResponseEntity.noContent();
    }

    @DoGet("/get-by-id")
    public VoucherResponse getById(@RequestParam Long id) {
        return voucherService.getById(id);
    }

    @DoGet("/find-by-field")
    public Map<String, Object> searchVoucher(
            @RequestParam("title") @Nullable String title,
            @RequestParam("voucherType") @Nullable VoucherType voucherType,
            @RequestParam("value") @Nullable String value,
            @RequestParam("from") @Nullable String from,
            @RequestParam("to") @Nullable String to,
            @RequestParam("status") @Nullable EntityStatus status,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) throws ParseException {
        SearchVoucherData searchVoucherData = SearchVoucherData
                .builder()
                .title(title)
                .value(value)
                .voucherType(voucherType)
                .from(from == null
                        ? null : DateUtil.parseToLocalDateTimeFromStringFormat(from, DateUtil.DATE_TIME_PATTERN))
                .to(to == null
                        ? null : DateUtil.parseToLocalDateTimeFromStringFormat(to, DateUtil.DATE_TIME_PATTERN))
                .status(status)
                .page(page)
                .size(size)
                .build();
        return voucherService.getByField(searchVoucherData);
    }

    @DoGet("/find-my-voucher-by-field")
    public Map<String, Object> searchMyVoucher(
            @RequestParam("title") @Nullable String title,
            @RequestParam("voucherType") @Nullable VoucherType voucherType,
            @RequestParam("value") @Nullable String value,
            @RequestParam("from") @Nullable String from,
            @RequestParam("to") @Nullable String to,
            @RequestParam("status") @Nullable EntityStatus status,
            @UserId Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) throws ParseException {
        SearchVoucherData searchVoucherData = SearchVoucherData
                .builder()
                .title(title)
                .value(value)
                .voucherType(voucherType)
                .from(from == null ?
                        null : DateUtil.parseToLocalDateTimeFromStringFormat(from, DateUtil.DATE_TIME_PATTERN))
                .to(to == null ?
                        null : DateUtil.parseToLocalDateTimeFromStringFormat(to, DateUtil.DATE_TIME_PATTERN))
                .status(status)
                .createdId(userId)
                .page(page)
                .size(size)
                .build();
        return voucherService.getByField(searchVoucherData);
    }
}
