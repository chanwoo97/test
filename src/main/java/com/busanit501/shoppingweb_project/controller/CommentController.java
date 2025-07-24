package com.busanit501.shoppingweb_project.controller;

import com.busanit501.shoppingweb_project.domain.Comment;
import com.busanit501.shoppingweb_project.dto.CommentRequestDTO;
import com.busanit501.shoppingweb_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public String createComment(@RequestBody CommentRequestDTO dto) {
        commentService.save(dto);
        return "댓글이 저장되었습니다.";
    }

    // 댓글 목록 조회 (상품 기준)
    @GetMapping("/product/{productId}")
    public List<Comment> getComments(@PathVariable Long productId) {
        return commentService.getCommentsByProduct(productId);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "댓글이 삭제되었습니다.";
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public String updateComment(@PathVariable Long commentId, @RequestBody String newContent) {
        commentService.updateComment(commentId, newContent);
        return "댓글이 수정되었습니다.";
    }
}