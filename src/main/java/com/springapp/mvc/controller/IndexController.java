package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by adel on 24.05.14.
 */
@RequestMapping("/")
@Controller
public class IndexController {
    @RequestMapping
    public String index(){
        return "index";
    }
}
