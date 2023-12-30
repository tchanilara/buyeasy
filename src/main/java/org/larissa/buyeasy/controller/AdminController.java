package org.larissa.buyeasy.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.larissa.buyeasy.database.entity.Product;
import org.larissa.buyeasy.formbean.CreateProductFormBean;
import org.larissa.buyeasy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("/admin/product")
    public ModelAndView createProduct(@RequestParam(required = false) String success){
        ModelAndView response = new ModelAndView();
        response.setViewName("admin/product");
        if (!StringUtils.isEmpty(success)) {
            response.addObject("success", success);
        }
        return response;
    }
    @GetMapping("/admin/productSubmit")
    public ModelAndView createProductSubmit(@Valid CreateProductFormBean form, BindingResult bindingResult, HttpSession session)  {


        if(bindingResult.hasErrors()){
            log.info("In add product submit has errors");
            ModelAndView response =  new ModelAndView("admin/product");
            for(ObjectError error: bindingResult.getAllErrors()){
                log.info("error: "+ error.getDefaultMessage());

            }
            response.addObject("form",form);
            response.addObject("errors", bindingResult);
            response.addObject("size", 0);
            return response;
        }


        Product p =  productService.addProduct(form);



        ModelAndView response =  new ModelAndView("admin/product");
        response.setViewName("redirect:product?success=Product Added Successfully");
        response.addObject("size", 0);


        return response;
    }
}
