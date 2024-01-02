package org.larissa.buyeasy.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.larissa.buyeasy.database.dao.ProductDAO;
import org.larissa.buyeasy.database.dao.UserDAO;
import org.larissa.buyeasy.database.entity.OrderProduct;
import org.larissa.buyeasy.database.entity.Product;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.formbean.CreateProductFormBean;
import org.larissa.buyeasy.formbean.RegisterUserFormBean;
import org.larissa.buyeasy.security.AuthenticatedUserService;
import org.larissa.buyeasy.service.CartService;
import org.larissa.buyeasy.service.ProductService;
import org.larissa.buyeasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@Slf4j
public class ProductController {


    @Autowired
    private ProductDAO productDao ;
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;




    @GetMapping("/")
    public ModelAndView listProduct(){
        ModelAndView response = new ModelAndView();
        response.setViewName("index");
        log.info("In List Product");
        List<Product> products = productDao.findAllProducts();

        response.addObject("productVar", products);
        response.addObject("size", cartService.getSizeCart());

        return response;
    }



    @RequestMapping("/product/detail")
    public ModelAndView detail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("product/detail");

        Product product = productDao.findById(id);

        if (product == null) {
            log.warn("Product with id " + id + " was not found");
            response.setViewName("redirect:/error/404");
            return response;
        }

        response.addObject("product", product);
        response.addObject("size", cartService.getSizeCart());

        return response;
    }

    @GetMapping("/product/search")
    public ModelAndView search(@RequestParam(required = false) String search) {
        ModelAndView response = new ModelAndView("product/search");

        log.debug("in the product search controller method : search pattern = " + search );

        if (!StringUtils.isEmpty(search) ) {

            response.addObject("search", search);

            if (!StringUtils.isEmpty(search)) {
                search = "%" + search + "%";
            }

            List<Product> products = productDao.findBySearch(search);

            response.addObject("productVar", products);
            response.addObject("size", cartService.getSizeCart());

        }

        return response;
    }
}
