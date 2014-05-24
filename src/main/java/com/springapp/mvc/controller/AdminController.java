package com.springapp.mvc.controller;

import com.springapp.mvc.Admin;
import com.springapp.mvc.aspect.ProceedIfLoggedIn;
import com.springapp.mvc.aspect.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping
    @Secured
    public String admin(){
        return "admin";
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
}
