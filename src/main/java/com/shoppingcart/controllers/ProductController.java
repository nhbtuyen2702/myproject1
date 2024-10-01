package com.shoppingcart.controllers;

import com.shoppingcart.entities.Product;
import com.shoppingcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // API to initialize products
    @PostMapping("/init-products")
    public ResponseEntity<String> initProducts() {
        productService.initProducts();
        return ResponseEntity.status(HttpStatus.OK).body("Products initialized!");
    }

    // API to view all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    // API to find product by id
    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product.toString());
    }

    // API to create new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // API to update product (full update)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }

        Product savedProduct = productService.updateProduct(product, updatedProduct);
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct.toString());
    }

    // API to partially update product
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchProduct(@PathVariable Long id, @RequestBody Product partialUpdate) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }

        Product updatedProduct = productService.partialUpdateProduct(product, partialUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct.toString());
    }

    // API to delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product with id " + id + " deleted!");
    }
}
