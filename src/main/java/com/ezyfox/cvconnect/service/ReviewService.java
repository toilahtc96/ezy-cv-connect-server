package com.ezyfox.cvconnect.service;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.model.AddReviewData;
import com.ezyfox.cvconnect.model.EditReviewData;
import com.ezyfox.cvconnect.response.ReviewResponse;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    void addReview(AddReviewData addReviewData, long userId);

    void editReview(EditReviewData editReviewData);

    ReviewResponse getById(long reviewId);

    List<ReviewResponse> getByStar(int star);

    List<ReviewResponse> getAll();

    List<ReviewResponse> getByStatus(EntityStatus status);

    List<ReviewResponse> getByType(int type);

    List<ReviewResponse> getByObjectId(long objectId);

    Map<String, Object> findByField(int size, int page, Long objectId, Long userReviewId);
}
