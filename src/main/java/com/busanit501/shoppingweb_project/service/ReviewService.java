package com.busanit501.shoppingweb_project.service;

import com.busanit501.shoppingweb_project.domain.Product;
import com.busanit501.shoppingweb_project.domain.Review;
import com.busanit501.shoppingweb_project.dto.ReviewRequestDTO;
import com.busanit501.shoppingweb_project.repository.ProductRepository;
import com.busanit501.shoppingweb_project.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    // 리뷰 저장
    public void save(ReviewRequestDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        Review review = Review.builder()
                .reviewContent(dto.getContent())
                .rating(dto.getRating())
                .product(product)
                .build();

        reviewRepository.save(review); // 정상 작동
    }

    // 리뷰 조회 (상품 기준)
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProduct_ProductId(productId);
    }

    // 리뷰 수정
    public void updateReview(Long reviewId, String newContent) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));
        review.setReviewContent(newContent);
        reviewRepository.save(review);
    }

    // 리뷰 삭제
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
