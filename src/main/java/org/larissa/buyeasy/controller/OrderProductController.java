package org.larissa.buyeasy.controller;

import lombok.extern.slf4j.Slf4j;
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
        List<OrderProduct> carts = cartService.addProductToOrder(id, qty);
        log.info("In order view. cart  size " + carts.size());

        List<String> totals = cartService.getTotal( carts);

        response.addObject("cartVar", carts);
        response.addObject("size", carts.size());
        response.addObject("subtotal", totals.get(0));
        response.addObject("shipping", totals.get(1));
        response.addObject("taxes", totals.get(2));
        response.addObject("total", totals.get(3));
        return response;
    }


    @RequestMapping("/order/viewcart")
    public ModelAndView viewcart() {
        ModelAndView response = new ModelAndView("order/viewcart");

        //retrieve a current shopping cart
        List<OrderProduct> carts = cartService.getProductCart();

        List<String> totals = cartService.getTotal( carts);

        response.addObject("cartVar", carts);
        response.addObject("size", carts.size());
        response.addObject("subtotal", totals.get(0));
        response.addObject("shipping", totals.get(1));
        response.addObject("taxes", totals.get(2));
        response.addObject("total", totals.get(3));
        return response;
    }

    @RequestMapping("/order/remove")
    public ModelAndView removeProduct(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("order/viewcart");
        //add product and retrieve a current shopping cart
        List<OrderProduct> carts = cartService.removeProductToOrder(id);
        log.info("In order view. cart  size " + carts.size());

        List<String> totals = cartService.getTotal( carts);

        response.addObject("cartVar", carts);
        response.addObject("size", carts.size());
        response.addObject("subtotal", totals.get(0));
        response.addObject("shipping", totals.get(1));
        response.addObject("taxes", totals.get(2));
        response.addObject("total", totals.get(3));
         return response;
    }

    @RequestMapping("/order/detail")
    public ModelAndView detail(@RequestParam Integer orderid) {
        ModelAndView response = new ModelAndView("order/detail");

        List<OrderProduct> carts = cartService.findByOrderId(orderid);

        response.addObject("cartVar", carts);
        response.addObject("orderid", orderid);
        response.addObject("size", cartService.getSizeCart() );

        return response;
    }

    @RequestMapping("/order/update")
    public ModelAndView updateQtyProduct(@RequestParam Integer id, Integer qty) {
        ModelAndView response = new ModelAndView("order/viewcart");
        if(qty < 1)
            return response;
        //add product and retrieve a current shopping cart
        List<OrderProduct> carts = cartService.updateProductToOrder(id, qty);
        log.info("In order view. cart  size " + carts.size());


        List<String> totals = cartService.getTotal( carts);

        response.addObject("cartVar", carts);
        response.addObject("size", carts.size());
        response.addObject("subtotal", totals.get(0));
        response.addObject("shipping", totals.get(1));
        response.addObject("taxes", totals.get(2));
        response.addObject("total", totals.get(3));
        return response;
    }
}

