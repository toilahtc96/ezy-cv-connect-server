package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.model.AddProgressData;
import com.ezyfox.cvconnect.model.EditProgressData;
import com.ezyfox.cvconnect.model.SearchProgressData;
import com.ezyfox.cvconnect.response.ProgressResponse;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProgressService {

    void addProgress(AddProgressData addProgressData);

    void editProgress(EditProgressData editProgressData);

    List<ProgressResponse> getByAgencyId(long agencyId);

    List<ProgressResponse> getByCandidateId(long candidateId);

    List<ProgressResponse> getAll();

    Map<String, Object> getPageActiveOfAgency(
            long agencyId,
            Long companyId,
            String fromCandidateTime,
            String toCandidateTime,
            String sendCompanyFrom,
            String sendCompanyTo,
            int page,
            int size
    ) throws ParseException;

    Map<String, Object> getProgressPaging(SearchProgressData searchProgressData);

    ProgressResponse getById(long id);

    List<ProgressResponse> getByCandidateJob(long candidateId, long jobId);

    void updateCvLink(long candidateId, long agencyId, long jobId, String cvLink);
}
