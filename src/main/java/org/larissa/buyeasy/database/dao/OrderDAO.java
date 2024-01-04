package org.larissa.buyeasy.database.dao;

import jakarta.transaction.Transactional;
import org.larissa.buyeasy.database.entity.Order;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

    Order findById(Integer id);
    @Query("SELECT o FROM Order o WHERE o.user.id = :id AND o.status = :status")
    Order findCurrentOrder(Integer id, String status);

    @Query("SELECT o FROM Order o WHERE o.user.id = :id")
    List<Order> findByUserId(Integer id);

    @Modifying
    @Transactional
    int deleteByComments(String comments);


    @Transactional
    Order findByComments(String comments);
}
