package com.cdweb.vocabproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/forgot-password"})
public class ForgotPassController {


    @GetMapping(value = {"","/"})
    public String ForgotPassPage(Model model){
        return "forgot-password";
    }
}
