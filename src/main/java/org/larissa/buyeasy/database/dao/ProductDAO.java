package org.larissa.buyeasy.database.dao;

import org.larissa.buyeasy.database.entity.Product;
import org.larissa.buyeasy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {

    Product findById(Integer id);
}
