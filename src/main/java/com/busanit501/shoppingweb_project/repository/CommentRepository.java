package com.busanit501.shoppingweb_project.repository;

import com.busanit501.shoppingweb_project.domain.Comment;
import com.busanit501.shoppingweb_project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProduct(Product product);
}
