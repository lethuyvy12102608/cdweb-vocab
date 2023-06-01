package com.cdweb.vocabproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping(value = {"","/"})
    public String homePage(Model model){
        return "home";
    }
}
