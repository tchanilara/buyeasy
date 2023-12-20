package org.larissa.buyeasy.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.larissa.buyeasy.database.dao.UserDAO;
import org.larissa.buyeasy.database.entity.User;
import org.larissa.buyeasy.formbean.RegisterUserFormBean;
import org.larissa.buyeasy.security.AuthenticatedUserService;
import org.larissa.buyeasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;
    @GetMapping("/auth/register")
    public ModelAndView register(){
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/register");
        return response;
    }

    @GetMapping("/auth/login")
    public ModelAndView login(){
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/login");
        return response;
    }

    @PostMapping("/auth/registerSubmit")
    public ModelAndView registerUserSubmit(@Valid RegisterUserFormBean form, BindingResult bindingResult, HttpSession session)  {


        if(bindingResult.hasErrors()){
            log.info("In create user submit has errors");
            ModelAndView response =  new ModelAndView("auth/register");
            for(ObjectError error: bindingResult.getAllErrors()){
                log.info("error: "+ error.getDefaultMessage());

            }
            response.addObject("form",form);
            response.addObject("errors", bindingResult);
            return response;
        }


        User u =  userService.createNewUser(form);


        authenticatedUserService.authenticateNewUser(session, u.getEmail(), form.getPassword());
        log.info("In create User with incoming args");
        ModelAndView response =  new ModelAndView("auth/register");
        response.setViewName("redirect:/");

        return response;
    }
}
