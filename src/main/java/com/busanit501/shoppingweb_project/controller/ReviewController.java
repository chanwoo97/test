package com.busanit501.shoppingweb_project.controller;

import com.busanit501.shoppingweb_project.domain.Review;
import com.busanit501.shoppingweb_project.dto.ReviewRequestDTO;
import com.busanit501.shoppingweb_project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public String createReview(@RequestBody ReviewRequestDTO dto) {
        log.info("리뷰 생성 요청: " + dto);
        reviewService.save(dto);
        log.info("리뷰 저장 완료");
        return "리뷰가 저장되었습니다.";
    }

    // 특정 상품의 리뷰 목록 조회
    @GetMapping("/product/{productId}")
    public List<Review> getReviews(@PathVariable Long productId) {
        log.info("상품 ID()에 대한 리뷰 목록 조회 요청" + productId);
        List<Review> reviews = reviewService.getReviewsByProduct(productId);
        log.info("조회된 리뷰 개수 : " + reviews.size());
        return reviews;
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public String updateReview(@PathVariable Long reviewId, @RequestBody String newContent) {
        reviewService.updateReview(reviewId, newContent);
        log.info("리뷰 수정 완료 -> ID : " + reviewId);
        return "리뷰가 수정되었습니다.";
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {
        log.info("리뷰 삭제 요청 - ID : " + reviewId);
        reviewService.deleteReview(reviewId);
        log.info("리뷰 삭제 완료 - ID : " + reviewId);
        return "리뷰가 삭제되었습니다.";
    }
}