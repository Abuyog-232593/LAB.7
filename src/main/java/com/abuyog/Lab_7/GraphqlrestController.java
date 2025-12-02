package com.abuyog.Lab_7;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/graphql")
public class GraphQLRestController {

    private final ProductService productService;

    public GraphQLRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> handleGraphQL(@RequestBody Map<String, Object> request) {
        try {
            String query = (String) request.get("query");

            if (query == null || query.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("errors", "Query cannot be empty"));
            }

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();

            if (query.contains("getAllProducts")) {
                List<Product> products = productService.getAllProducts();
                data.put("getAllProducts", products);
            }
            else {
                return ResponseEntity.badRequest().body(Map.of("errors", "Unsupported query"));
            }

            response.put("data", data);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("errors", "Error: " + e.getMessage()));
        }
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("message", "Postman endpoint working");
    }
}