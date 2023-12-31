package org.larissa.buyeasy.controller;

import lombok.extern.slf4j.Slf4j;
import org.larissa.buyeasy.database.entity.Order;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.service.CartService;
import org.larissa.buyeasy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;
    @RequestMapping("/order/checkout")
    public ModelAndView checkout() {
        ModelAndView response = new ModelAndView("order/viewcart");

        if (orderService.checkout() != null){
            response.setViewName("redirect:viewcart?success=Order Placed Successfully");
        }else{
            response.setViewName("redirect:viewcart?error=Something wrong happened. Please try again.");
        }

        response.addObject("size", cartService.getSizeCart());

        return response;
    }

    @RequestMapping("/order/history")
    public ModelAndView history() {
        ModelAndView response = new ModelAndView("order/history");

        List<Order> orders =orderService.history();

        response.addObject("cartVar", orders);
        response.addObject("size", cartService.getSizeCart());

        return response;
    }

    @RequestMapping("/order/viewcart")
    public ModelAndView viewcart(@RequestParam(required = false) String success, @RequestParam(required = false) String error) {
        ModelAndView response = new ModelAndView("order/viewcart");

        //retrieve a current shopping cart
        Order order = orderService.getCurrentOrder();

        if (!StringUtils.isEmpty(success)) {
            response.addObject("success", success);
        }
        if (!StringUtils.isEmpty(success)) {
            response.addObject("error", error);
        }

        if(order == null){
            response.addObject("size", 0);
            return response;
        }

        response.addObject("cartVar", order);
        response.addObject("size", order.getNumberOfProducts());
        return response;
    }

    @RequestMapping("/order/detail")
    public ModelAndView detail(@RequestParam Integer orderid) {
        ModelAndView response = new ModelAndView("order/detail");

        Order order = orderService.findById(orderid);


        if(order == null){
            response.addObject("size", 0);
            return response;
        }


        log.info("In order view. cart  size " + order.getNumberOfProducts());

        response.addObject("cartVar", order);
        response.addObject("size", cartService.getSizeCart());

        return response;
    }

}
