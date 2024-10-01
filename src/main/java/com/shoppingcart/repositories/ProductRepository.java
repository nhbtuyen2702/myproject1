package com.shoppingcart.repositories;

import com.shoppingcart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE id = ?1", nativeQuery = true)
    Product findByIdNative(Long id);

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAllNative();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product (name, price) VALUES (?1, ?2)", nativeQuery = true)
    void insertProductNative(String name, double price);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET name = ?2, price = ?3 WHERE id = ?1", nativeQuery = true)
    void updateProductNative(Long id, String name, double price);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product WHERE id = ?1", nativeQuery = true)
    void deleteProductNative(Long id);
}
