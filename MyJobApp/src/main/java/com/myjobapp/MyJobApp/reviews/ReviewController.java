package com.myjobapp.MyJobApp.reviews;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> saveReview(@PathVariable Long companyId,@RequestBody Review review){
        boolean isReviewSaved = reviewService.saveReview(companyId,review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review saved successfully",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review not saved",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId,reviewId);

        if(review!=null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> removeReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review updatedReview){
        if(reviewService.updateReview(companyId,reviewId,updatedReview)){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);

        }
    }


}
