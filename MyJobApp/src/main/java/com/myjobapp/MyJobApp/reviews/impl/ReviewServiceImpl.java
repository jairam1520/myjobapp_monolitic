package com.myjobapp.MyJobApp.reviews.impl;

import com.myjobapp.MyJobApp.company.Company;
import com.myjobapp.MyJobApp.company.CompanyService;
import com.myjobapp.MyJobApp.reviews.Review;
import com.myjobapp.MyJobApp.reviews.ReviewRepo;
import com.myjobapp.MyJobApp.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepo reviewRepo;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepo reviewRepo,CompanyService companyService) {
        this.reviewRepo = reviewRepo;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepo.findAllByCompanyId(companyId);
    }

    @Override
    public boolean saveReview(Long companyId,Review review) {
        Company company = companyService.fetchCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        Company company = companyService.fetchCompanyById(companyId);
        if(company!=null){
            List<Review> reviewList = company.getReviews();

            return reviewList.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.fetchCompanyById(companyId)!=null && reviewRepo.existsById(reviewId)){
            //find the company for which review needs to be deleted
            Review review = reviewRepo.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);//remove review for that company
            review.setCompany(null);
            companyService.updateCompany(companyId,company);
            reviewRepo.deleteById(reviewId);//remove review from master data
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.fetchCompanyById(companyId)!=null){
            updatedReview.setCompany(companyService.fetchCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepo.save(updatedReview);
            return true;
        }
        else{
            return false;
        }
    }
}
