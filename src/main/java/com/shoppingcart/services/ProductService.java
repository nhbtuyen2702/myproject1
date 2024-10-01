package com.shoppingcart.services;

import com.shoppingcart.entities.Product;
import com.shoppingcart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAllNative();
    }

    public Product getProductById(Long id) {
        return productRepository.findByIdNative(id);
    }

    @Transactional
    public Product saveProduct(Product product) {
        productRepository.insertProductNative(product.getName(), product.getPrice());
        return product;  // Lưu ý sẽ không có `id` tự động trả về ngay.
    }

    @Transactional
    public Product updateProduct(Product existingProduct, Product updatedProduct) {
        productRepository.updateProductNative(existingProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice());
        return existingProduct;
    }

    @Transactional
    public Product partialUpdateProduct(Product existingProduct, Product partialUpdate) {
        if (partialUpdate.getName() != null) {
            existingProduct.setName(partialUpdate.getName());
        }
        if (partialUpdate.getPrice() != 0) {
            existingProduct.setPrice(partialUpdate.getPrice());
        }
        productRepository.updateProductNative(existingProduct.getId(), existingProduct.getName(), existingProduct.getPrice());
        return existingProduct;
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        Product existingProduct = productRepository.findByIdNative(id);
        if (existingProduct != null) {
            productRepository.deleteProductNative(id);
            return true;
        }
        return false;
    }

    // Method to initialize products
    public void initProducts() {
        for (int i = 1; i <= 10; i++) {
            Product product = new Product("Product " + i, i * 10.0);
            productRepository.insertProductNative(product.getName(), product.getPrice());
        }
    }
}
