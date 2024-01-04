package org.larissa.buyeasy.database.dao;

import org.junit.jupiter.api.*;
import org.larissa.buyeasy.database.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderDAOTest {

    @Autowired
    OrderDAO orderDao;

    @Test
    @Order(1)
    public void createNewOrderTest(){
        Date createdDate = new Date(2024,1,1);

        //given
        org.larissa.buyeasy.database.entity.Order order = new org.larissa.buyeasy.database.entity.Order();
        order.setOrderDate(createdDate);
        order.setStatus("Cart");
        order.setComments("Test comments 123457");

        //when
        order = orderDao.save(order);

        //then
        Assertions.assertNotNull(order.getId());
        Assertions.assertEquals("Cart", order.getStatus());
        Assertions.assertEquals("Test comments 123457", order.getComments());
        Assertions.assertEquals(createdDate, order.getOrderDate());
        Assertions.assertNull(order.getUser());

    }


    @Test
    @Order(2)
    public void findByCommentsTest() {
        // given
        String comment = "Test comments 123457";

        // when
        org.larissa.buyeasy.database.entity.Order order  = orderDao.findByComments(comment);

        // then
        Assertions.assertNotNull(order.getId());
        Assertions.assertEquals("Cart", order.getStatus());
        Assertions.assertEquals("Test comments 123457", order.getComments());
        Assertions.assertEquals(new Date(2024,1,1), order.getOrderDate());
        Assertions.assertNull(order.getUser());
    }
    @Test
    @Order(3)
    public  void deleteOrderTest(){
        //given
        String comment = "Test comments 123457";

        //when
        int deleted = orderDao.deleteByComments(comment);

        //then
        Assertions.assertEquals(1, deleted);

    }

    @Test
    @Order(4)
    public void shouldNotExistTest() {
        // given
        String comment = "Test comments 123457";

        // when
        org.larissa.buyeasy.database.entity.Order order  = orderDao.findByComments(comment);

        // then
        Assertions.assertNull(order);
    }
}
