package com.busanit501.shoppingweb_project.service;

import com.busanit501.shoppingweb_project.domain.Product;
import com.busanit501.shoppingweb_project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    // DB와 연동하는 JPA Repository 주입
    private final ProductRepository productRepository;

    // 상품 저장 (DB에 INSERT)
    public void saveProduct(Product product) {
        productRepository.save(product); // JPA save 메서드를 통해 DB에 저장
    }

    // 전체 상품 조회 (SELECT * FROM product)
    public List<Product> findAllProducts() {
        return productRepository.findAll(); // 모든 상품 리스트를 DB에서 가져옴
    }

    // 상품 ID로 조회 (SELECT * FROM product WHERE id = ?)
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null); // 해당 ID가 없으면 null 반환
    }

    // 상품 정보 수정
    public void updateProduct(Long id, Product updatedProduct) {
        // 기존 상품을 DB에서 찾기
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            // 기존 상품의 필드 값을 새 값으로 업데이트
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());
            existingProduct.setProductTag(updatedProduct.getProductTag());

            // 변경된 내용을 다시 DB에 저장 (UPDATE)
            productRepository.save(existingProduct);
        }
    }

    // 상품 삭제 (DELETE FROM product WHERE id = ?)
    public void deleteProduct(Long id) {
        productRepository.deleteById(id); // ID 기준으로 상품 삭제
    }
}
