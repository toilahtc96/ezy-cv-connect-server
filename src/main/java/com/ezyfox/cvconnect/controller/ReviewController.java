package com.ezyfox.cvconnect.controller;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.RequestToDataConverter;
import com.ezyfox.cvconnect.request.AddReviewRequest;
import com.ezyfox.cvconnect.request.EditReviewRequest;
import com.ezyfox.cvconnect.response.ReviewResponse;
import com.ezyfox.cvconnect.service.ReviewService;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Controller("api/v1/review")
@AllArgsConstructor
@Authenticated
public class ReviewController {

    private final ReviewService reviewService;
    private final RequestToDataConverter requestToDataConverter;

    @DoPost("/add")
    public ResponseEntity addReview(@RequestBody AddReviewRequest addReviewRequest) {
        reviewService.addReview(requestToDataConverter.toDataFromAddReview(addReviewRequest));
        return ResponseEntity.noContent();
    }

    @DoPost("/edit")
    public ResponseEntity editReview(@RequestBody EditReviewRequest editReviewRequest) {
        reviewService.editReview(requestToDataConverter.toDataFromEditReview(editReviewRequest));
        return ResponseEntity.noContent();
    }

    @DoGet("/get-all")
    public List<ReviewResponse> getAll() {
        return reviewService.getAll();
    }

    @DoGet("/get-by-id")
    public ReviewResponse getById(@RequestParam long reviewId) {
        return reviewService.getById(reviewId);
    }

    @DoGet("/get-by-star")
    public List<ReviewResponse> getByStar(@RequestParam int star) {
        return reviewService.getByStar(star);
    }

    @DoGet("/get-by-type")
    public List<ReviewResponse> getByType(@RequestParam int type) {
        return reviewService.getByType(type);
    }

    @DoGet("/get-active")
    public List<ReviewResponse> getReviewActive() {
        return reviewService.getByStatus(EntityStatus.ACTIVE);
    }

    @DoGet("/get-by-object-id")
    public List<ReviewResponse> getByObject(@RequestParam long objectId) {
        return reviewService.getByObjectId(objectId);
    }
}
