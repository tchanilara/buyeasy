package org.larissa.buyeasy.service;

import org.larissa.buyeasy.database.dao.OrderDAO;
import org.larissa.buyeasy.database.entity.Order;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.formbean.ViewCartFormBean;
import org.larissa.buyeasy.security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDao;

    @Autowired
    AuthenticatedUserService authenticatedUserService;

    public Order loadCurrentOrder(Integer userId){
        Order order = orderDao.findCurrentOrder(userId,"Cart");
        return order;

    }
    public  Order createNewOrder(){
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus("Cart");
        order.setUser(authenticatedUserService.loadCurrentUser());
        return orderDao.save(order);

    }

    public  void checkout(){
        Integer userId = authenticatedUserService.loadCurrentUser().getId();
        Order order = orderDao.findCurrentOrder(userId, "Cart");
        if(order != null){
            order.setStatus("On Hold");
            orderDao.save(order);
        }

    }
    public  List<Order>  history(){
        Integer userId = authenticatedUserService.loadCurrentUser().getId();
        return orderDao.findByUserId(userId);

    }

}
