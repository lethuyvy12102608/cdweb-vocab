package com.cdweb.vocabproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/profile"})

public class ProfileController {


    @GetMapping(value = {"","/"})
    public String profilePage(Model model){
    return "profile";
    }
}
