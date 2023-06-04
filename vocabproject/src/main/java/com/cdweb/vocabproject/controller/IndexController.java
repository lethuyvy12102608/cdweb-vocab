package com.cdweb.vocabproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/index"})
public class IndexController {


    @GetMapping(value = {"","/"})
    public String indexPage(Model model){
        return "index";
    }

}
