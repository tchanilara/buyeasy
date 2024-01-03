package org.larissa.buyeasy.service;

import org.larissa.buyeasy.database.dao.OrderDAO;
import org.larissa.buyeasy.database.dao.OrderProductDAO;
import org.larissa.buyeasy.database.dao.ProductDAO;
import org.larissa.buyeasy.database.entity.Order;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.security.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {

    @Autowired
    OrderProductDAO orderProductDao;

    @Autowired
    OrderDAO orderDao;

    @Autowired
    ProductDAO productDao;

    @Autowired
    AuthenticatedUserService authenticatedUserService;

    @Autowired
    OrderService orderService;

    public List<OrderProduct> findByOrderId(Integer orderId){
        //from order current get list of order details
        List<OrderProduct> orderProducts = orderProductDao.findByOrderId(orderId);

        return orderProducts;

    }

    public  OrderProduct createNewOrderProduct(Integer qty, Integer productId, Integer userId){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setQuantityOrdered(qty);
        orderProduct.setProduct(productDao.findById(productId));
        orderProduct.setOrder(orderDao.findById(userId));
        return orderProductDao.save(orderProduct);
    }

    public  OrderProduct updateQty(Integer qty, Integer id){
        OrderProduct orderProduct = orderProductDao.findById(id);
        orderProduct.setQuantityOrdered(qty);
        return orderProductDao.save(orderProduct);
    }

    public Order addProductToOrder(Integer productId, Integer qty){

        Integer userId = authenticatedUserService.loadCurrentUser().getId();
        Order order = orderDao.findCurrentOrder(userId,"Cart");

        //Create a new order if there is not a current open order, status for open order is Cart
        //create a new shopping cart or orderProduct
        if(order == null){
            order = orderService.createNewOrder();
            createNewOrderProduct(qty, productId, order.getId());

        }else{
            //retrieve the  current shopping cart
            OrderProduct orderProduct = orderProductDao.findByOrderIdProductId(productId, order.getId());
            if(orderProduct == null){
                createNewOrderProduct(qty, productId, order.getId());
            }else {
                //this product is already inside the shopping just update the quantity
                updateQty(qty, orderProduct.getId());
            }
        }
        return  orderDao.findById(order.getId());

    }

    public List<OrderProduct> getProductCart(){

        List<OrderProduct> carts = new ArrayList<>();
        Integer userId = authenticatedUserService.loadCurrentUser().getId();
        Order order = orderDao.findCurrentOrder(userId,"Cart");

        //Create a new order if there is not a current open order, status for open order is Cart
        //create a new shopping cart or orderProduct
        if(order != null){
                //retrieve or find all product for this order
                carts = findByOrderId(order.getId());
        }
        return  carts;

    }

    public Order removeProductToOrder(Integer productId) {

        List<OrderProduct> carts = new ArrayList<>();
        Integer userId = authenticatedUserService.loadCurrentUser().getId();
        Order order = orderDao.findCurrentOrder(userId, "Cart");
        if (order != null) {
            OrderProduct orderProduct = orderProductDao.findByOrderIdProductId(productId, order.getId());
            orderProductDao.delete(orderProduct);
            return orderDao.findById(order.getId());
        }

        return order;
    }
    public  int getSizeCart(){
        User user = authenticatedUserService.loadCurrentUser();
        if(user == null){
            return 0;
        }

        List<OrderProduct> carts =getProductCart();

        return carts.isEmpty() ? 0 : carts.size();
    }

    public Order updateProductToOrder(Integer productId, Integer qty){

        Integer userId = authenticatedUserService.loadCurrentUser().getId();
        Order order = orderDao.findCurrentOrder(userId,"Cart");

        if(order != null){
            //retrieve the  current shopping cart
            OrderProduct orderProduct = orderProductDao.findByOrderIdProductId(productId, order.getId());
            if(orderProduct != null){
                //this product is already inside the shopping just update the quantity
                return updateQty(qty, orderProduct.getId()).getOrder();
            }
        }
        return  order;

    }


}
