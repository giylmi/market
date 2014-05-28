package com.springapp.mvc.controller;

import com.springapp.mvc.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by adel on 24.05.14.
 */
@RequestMapping("/")
@Controller
public class IndexController {

    @Autowired
    ProductDao dao;

    @RequestMapping
    public String index(Model model){
        model.addAttribute("products", dao.findAll());
        return "index";
    }

    @RequestMapping("product/{id}")
    public String product(@PathVariable Long id, Model model){
        model.addAttribute("product", dao.findOne(id));
        return "product";
    }
}
