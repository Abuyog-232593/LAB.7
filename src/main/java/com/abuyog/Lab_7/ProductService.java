package com.abuyog.Lab_7;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private Long idCounter = 1L;

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public Product createProduct(Product product) {
        product.setId(idCounter++);
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = products.get(id);

        if (existing == null) {
            return null;
        }

        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return existing;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
