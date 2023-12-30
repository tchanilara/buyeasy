package org.larissa.buyeasy.controller;

import lombok.extern.slf4j.Slf4j;
import org.larissa.buyeasy.database.dao.OrderProductDAO;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.database.entity.Product;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.security.AuthenticatedUserService;
import org.larissa.buyeasy.service.CartService;
import org.larissa.buyeasy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.larissa.buyeasy.database.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class OrderProductController {

    @Autowired
    CartService cartService;

    @RequestMapping("/order/add")
    public ModelAndView addProduct(@RequestParam Integer id, Integer qty) {
        ModelAndView response = new ModelAndView("order/viewcart");
        //add product and retrieve a current shopping cart
        List<OrderProduct> carts = cartService.addProductToOrder(id, qty);
        log.info("In order view. cart  size " + carts.size());

        double subTotal = 0.00;
        double shipping = 25.00;
        double taxes = 0.06;
        DecimalFormat df = new DecimalFormat("0.00");
        for (OrderProduct orderProduct : carts) {
            log.debug("orderP: id = " + orderProduct.getId() + " quantity = " + orderProduct.getQuantityOrdered());
             subTotal += orderProduct.getProduct().getPrice() * orderProduct.getQuantityOrdered();
        }

        taxes = taxes * (subTotal + shipping);
        response.addObject("cartVar", carts);
        response.addObject("size", carts.size());
        response.addObject("subtotal", df.format(subTotal));
        response.addObject("shipping", df.format(shipping));
         response.addObject("taxes", df.format(taxes));
         response.addObject("total", df.format(subTotal + taxes+ shipping)); 
        return response;
    }


    @RequestMapping("/order/viewcart")
    public ModelAndView viewcart() {
        ModelAndView response = new ModelAndView("order/viewcart");

        //retrieve a current shopping cart
        List<OrderProduct> carts = cartService.viewCart();

        double subTotal = 0.00;
        double shipping = 25.00;
        double taxes = 0.06;
        DecimalFormat df = new DecimalFormat("0.00");
        for (OrderProduct orderProduct : carts) {
             subTotal += orderProduct.getProduct().getPrice() * orderProduct.getQuantityOrdered();
        }
        taxes = taxes * (subTotal + shipping);
        response.addObject("cartVar", carts);
        response.addObject("size", carts.size());
        response.addObject("subtotal", df.format(subTotal));
        response.addObject("shipping", df.format(shipping));
         response.addObject("taxes", df.format(taxes));
         response.addObject("total", df.format(subTotal + taxes+ shipping));
        return response;
    }

    @RequestMapping("/order/remove")
    public ModelAndView removeProduct(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("order/viewcart");
        //add product and retrieve a current shopping cart
        List<OrderProduct> carts = cartService.removeProductToOrder(id);
        log.info("In order view. cart  size " + carts.size());

        double subTotal = 0.00;
        double shipping = 25.00;
        double taxes = 0.06;
        DecimalFormat df = new DecimalFormat("0.00");
        for (OrderProduct orderProduct : carts) {                                                      
             subTotal += orderProduct.getProduct().getPrice() * orderProduct.getQuantityOrdered();
        }
        taxes = taxes * (subTotal + shipping);
         response.addObject("cartVar", carts);
         response.addObject("size", carts.size());
         response.addObject("subtotal", df.format(subTotal));
         response.addObject("shipping", df.format(shipping));
         response.addObject("taxes", df.format(taxes));
         response.addObject("total", df.format(subTotal + taxes+ shipping));
         return response;
    }
}

