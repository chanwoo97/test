package com.busanit501.shoppingweb_project.service;

import com.busanit501.shoppingweb_project.domain.Comment;
import com.busanit501.shoppingweb_project.domain.Product;
import com.busanit501.shoppingweb_project.dto.CommentRequestDTO;
import com.busanit501.shoppingweb_project.repository.CommentRepository;
import com.busanit501.shoppingweb_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    // 댓글저장
    public void save(CommentRequestDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setWriter(dto.getWriter());
        comment.setProduct(product);

        commentRepository.save(comment);
    }

    // 상품별 댓글 조회
    public List<Comment> getCommentsByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        return commentRepository.findByProduct(product);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    // 댓글 수정
    public void updateComment(Long commentId, String newContent) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(newContent);
            commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
    }
}
