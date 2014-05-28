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
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("product/{id}")
    @Secured
    public String product(@PathVariable Long id, Model model){
        model.addAttribute("product", dao.findOne(id));
        return "product";
    }

    @RequestMapping
    @Secured
    public String admin(Model model){
        model.addAttribute("products", dao.findAll());
        model.addAttribute("newprod", new Product());
        return "admin";
    }

    @RequestMapping("add")
    @Secured
    public String add(@ModelAttribute Product newprod, Model model){
        dao.save(newprod);
        model.addAttribute("products", dao.findAll());
        model.addAttribute("product", new Product());
        return "admin";
    }

    @RequestMapping("delete/{id}")
    @Secured
    public String delete(@PathVariable Long id){
        dao.delete(dao.findOne(id));
        return "redirect:/admin";
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
