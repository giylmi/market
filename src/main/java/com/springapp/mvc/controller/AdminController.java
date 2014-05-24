package com.springapp.mvc.controller;

import com.springapp.mvc.Admin;
import com.springapp.mvc.aspect.ProceedIfLoggedIn;
import com.springapp.mvc.aspect.Secured;
import com.springapp.mvc.dao.ProductDao;
import com.springapp.mvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by adel on 24.05.14.
 */

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    Admin admin;
    @Autowired
    HttpSession session;
    @Autowired
    ProductDao dao;

    @RequestMapping
    @Secured
    public String admin(Model model){
        model.addAttribute("products", dao.findAll());
        model.addAttribute("newprod", new Product());
        return "admin";
    }

    @RequestMapping("add")
    @Secured
    public ResponseEntity<Product> add(@ModelAttribute Product newprod){
        dao.save(newprod);
        return new ResponseEntity<>(newprod, HttpStatus.OK);
    }

    @ProceedIfLoggedIn
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @ProceedIfLoggedIn
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loggedin(@RequestParam String login, @RequestParam String password, Model model){
        if (admin.getLogin().equals(login) && admin.getPassword().equals(password)){
            session.setAttribute("admin", admin);
            return "redirect:/admin";
        }
        model.addAttribute("error", true);
        return "login";
    }

    @Secured
    @RequestMapping(value = "logout")
    public String loggedout(){
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }
}
