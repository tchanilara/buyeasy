package org.larissa.buyeasy.database.dao;

import jakarta.transaction.Transactional;
import org.larissa.buyeasy.database.entity.Product;
import org.larissa.buyeasy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    Product findById(Integer id);

    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    @Query("SELECT p FROM Product p WHERE p.name LIKE :search or p.description LIKE :search")
    List<Product> findBySearch(String search);

    @Modifying
    @Transactional
    int deleteByName(String name);
}
