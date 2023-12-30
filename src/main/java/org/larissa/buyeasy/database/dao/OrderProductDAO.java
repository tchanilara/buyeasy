package org.larissa.buyeasy.database.dao;

import org.larissa.buyeasy.database.entity.Order;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.database.entity.Product;
import org.larissa.buyeasy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductDAO extends JpaRepository<OrderProduct, Long> {
    @Query("SELECT op FROM OrderProduct op WHERE op.order.id = :orderId")
    List<OrderProduct> findByOrderId(Integer orderId);

    OrderProduct findById(Integer id);


    @Query("SELECT op FROM OrderProduct op WHERE op.order.id = :orderId AND op.product.id = :productId")
    OrderProduct findByOrderIdProductId(Integer productId, Integer orderId);
}
