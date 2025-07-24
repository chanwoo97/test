package com.busanit501.shoppingweb_project.service; // 필요에 따라 패키지 조정

import com.busanit501.shoppingweb_project.domain.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public void saveProduct(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(counter.incrementAndGet()); // 새 ID 할당
        }
        products.add(product);
    }

    public List<Product> findAllProducts() {
        return new ArrayList<>(products); // 외부 수정 방지를 위해 사본 반환
    }

    public Product findProductById(Long id) {
        return products.stream()
                .filter(p -> p.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = findProductById(id);
        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setPrice(updatedProduct.getPrice());
        }
    }

    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getProductId().equals(id));
    }
}