package com.abuyog.Lab_7;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class ProductGraphQLController {

    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
        System.out.println("ProductGraphQLController initialized!");
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        System.out.println("GraphQL: getAllProducts called");
        return productService.getAllProducts();
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        System.out.println("GraphQL: getProductById called with id: " + id);
        return productService.getProductById(id);
    }

    @QueryMapping
    public Integer getProductCount() {
        System.out.println("GraphQL: getProductCount called");
        return productService.getProductCount();
    }

    @MutationMapping
    public Product createProduct(@Argument String name, @Argument Double price) {
        System.out.println("GraphQL: createProduct called with name: " + name + ", price: " + price);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productService.createProduct(product);
    }
}