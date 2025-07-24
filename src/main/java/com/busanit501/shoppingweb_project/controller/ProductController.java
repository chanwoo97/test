package com.busanit501.shoppingweb_project.controller;

import com.busanit501.shoppingweb_project.domain.Product;
import com.busanit501.shoppingweb_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 등록 (CREATE)
    @PostMapping
    public String createProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return "상품이 저장되었습니다.";
    }

    // 상품 전체 조회 (READ ALL)
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    // 상품 하나 조회 (READ ONE)
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    // 상품 수정 (UPDATE)
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return "상품이 수정되었습니다.";
    }

    // 상품 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "상품이 삭제되었습니다.";
    }
}