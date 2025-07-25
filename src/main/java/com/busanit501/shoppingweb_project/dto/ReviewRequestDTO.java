package com.busanit501.shoppingweb_project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {
    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
    private String content;

    @NotBlank(message = "작성자는 비워둘 수 없습니다.")
    private String writer;

    @NotNull(message = "어떤 상품에 댓글을 달 건지 지정해야 합니다.")
    private Long productId;

    @Min(value = 1, message = "평점은 1 이상이어야 합니다.")
    @Max(value = 5, message = "평점은 5 이하이어야 합니다.")
    private int rating;
}