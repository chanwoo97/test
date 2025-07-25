package com.busanit501.shoppingweb_project.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reviews") // 리뷰 정보를 저장할 테이블명 지정
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId; // 리뷰 고유 ID (자동 생성)

    @Column(nullable = false)
    private String reviewContent; // 리뷰 내용

    @Column(nullable = false)
    private int rating; // 리뷰 평점 (예: 1~5점)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 리뷰 생성 시각 (수정 불가)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // 이 리뷰가 달린 상품 정보

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // 저장 전에 생성일 자동 설정
    }
}