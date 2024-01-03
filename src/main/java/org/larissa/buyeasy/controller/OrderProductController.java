package org.larissa.buyeasy.controller;

import lombok.extern.slf4j.Slf4j;
import org.larissa.buyeasy.database.entity.Order;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@Slf4j
public class OrderProductController {

    @Autowired
    CartService cartService;

    @RequestMapping("/order/add")
    public ModelAndView addProduct(@RequestParam Integer id, Integer qty) {
        ModelAndView response = new ModelAndView("order/viewcart");
        if(qty < 1)
            return response;
        //add product and retrieve a current shopping cart
        Order order = cartService.addProductToOrder(id, qty);

        if(order == null){
            response.addObject("size", 0);
            return response;
        }

        log.info("In order view. cart  size " + order.getNumberOfProducts());

        response.addObject("cartVar", order);
        response.addObject("size", order.getNumberOfProducts());
        return response;
    }




    @RequestMapping("/order/remove")
    public ModelAndView removeProduct(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("order/viewcart");
        //add product and retrieve a current shopping cart
        Order order = cartService.removeProductToOrder(id);

        if(order == null){
            response.addObject("size", 0);
            return response;
        }

        log.info("In order view. cart  size " + order.getNumberOfProducts());

        response.addObject("cartVar", order);
        response.addObject("size", order.getNumberOfProducts());
         return response;
    }



    @RequestMapping("/order/update")
    public ModelAndView updateQtyProduct(@RequestParam Integer id, Integer qty) {
        ModelAndView response = new ModelAndView("order/viewcart");
        if(qty < 1)
            return response;
        //add product and retrieve a current shopping cart
        Order order = cartService.updateProductToOrder(id, qty);


        if(order == null){
            response.addObject("size", 0);
            return response;
        }


        log.info("In order view. cart  size " + order.getNumberOfProducts());

        response.addObject("cartVar", order);

        response.addObject("size", order.getNumberOfProducts());
        return response;
    }
}

