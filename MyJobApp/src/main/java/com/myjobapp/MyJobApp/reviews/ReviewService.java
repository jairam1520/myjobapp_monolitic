package com.myjobapp.MyJobApp.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean saveReview(Long companyId,Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean deleteReview(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review updatedReview);
}
