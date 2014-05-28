package com.springapp.mvc.controller;

import com.springapp.mvc.dao.ProductDao;
import com.springapp.mvc.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by adel on 24.05.14.
 */
@RequestMapping("/")
@Controller
public class IndexController {

    @Autowired
    ProductDao dao;
    @Autowired
    HttpSession session;

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

    @RequestMapping("bucket/add/{id}")
    public String add(@PathVariable Long id, Model model){
        Bucket bucket = (Bucket) session.getAttribute("bucket");
        bucket.add(dao.findOne(id));
        session.setAttribute("bucket", bucket);
        model.addAttribute("products", dao.findAll());
        return "index";
    }

    @RequestMapping("bucket/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        Bucket bucket = (Bucket) session.getAttribute("bucket");
        bucket.delete(dao.findOne(id));
        session.setAttribute("bucket", bucket);
        model.addAttribute("products", dao.findAll());
        return "index";
    }

    @RequestMapping("bucket")
    public String bucket(){
        return "bucket";
    }
}
