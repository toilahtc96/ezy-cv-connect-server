package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddDealData;
import com.ezyfox.cvconnect.model.EditDealData;
import com.ezyfox.cvconnect.response.DealResponse;

import java.util.List;

public interface DealService {

    void addDeal(AddDealData addDealData);

    void editDeal(EditDealData editDealData);

    List<DealResponse> getByAgencyId(long agencyId);

    List<DealResponse> getByCandidateId(long candidateId);

    List<DealResponse> getAll();

    List<DealResponse> getAllActiveOfAgency(long agencyId);
}
