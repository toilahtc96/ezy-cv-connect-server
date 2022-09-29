package com.ezyfox.cvconnect.service.impl;

import com.ezyfox.cvconnect.constant.EntityStatus;
import com.ezyfox.cvconnect.converter.DataToEntityConverter;
import com.ezyfox.cvconnect.converter.EntityToResponseConverter;
import com.ezyfox.cvconnect.entity.Review;
import com.ezyfox.cvconnect.exception.ResourceNotFoundException;
import com.ezyfox.cvconnect.model.AddReviewData;
import com.ezyfox.cvconnect.model.EditReviewData;
import com.ezyfox.cvconnect.repository.ReviewRepository;
import com.ezyfox.cvconnect.response.ReviewResponse;
import com.ezyfox.cvconnect.service.ReviewService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final EntityToResponseConverter entityToResponseConverter;
    private final DataToEntityConverter dataToEntityConverter;

    @Override
    public void addReview(AddReviewData addReviewData) {
        reviewRepository.save(dataToEntityConverter.dataToReview(addReviewData));
    }

    @Override
    public void editReview(EditReviewData editReviewData) {
        Review reviewById = reviewRepository.findById(editReviewData.getReviewId());
        if (reviewById == null) {
            throw new ResourceNotFoundException("Review");
        }
        reviewById.setDescription(reviewById.getDescription());
        reviewById.setStar(reviewById.getStar());
        reviewById.setObjectId(reviewById.getObjectId());
        reviewById.setReviewOwner(reviewById.getReviewOwner());
        reviewById.setType(reviewById.getType());
        reviewById.setStatus(reviewById.getStatus());
        reviewRepository.save(reviewById);
    }

    @Override
    public List<ReviewResponse> getByStar(int star) {
        return reviewRepository
            .getByStar(star)
            .stream()
            .map(entityToResponseConverter::toReviewResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getByStatus(EntityStatus status) {
        return reviewRepository
            .getByStatus(status)
            .stream()
            .map(entityToResponseConverter::toReviewResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getByType(int type) {
        return reviewRepository
            .getByType(type)
            .stream()
            .map(entityToResponseConverter::toReviewResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getByObjectId(long objectId) {
        return reviewRepository
            .getByObjectId(objectId)
            .stream()
            .map(entityToResponseConverter::toReviewResponse)
            .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse getById(long reviewId) {
        return entityToResponseConverter.toReviewResponse(reviewRepository.findById(reviewId));
    }

    @Override
    public List<ReviewResponse> getAll() {
        return reviewRepository
            .findAll()
            .stream()
            .map(entityToResponseConverter::toReviewResponse)
            .collect(Collectors.toList());
    }

}
