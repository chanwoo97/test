package com.busanit501.shoppingweb_project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    private String writer;

    // 댓글이 달리는 상품
    @ManyToOne
    @JoinColumn(name = "product_id") // 외래키
    private Product product;
}
