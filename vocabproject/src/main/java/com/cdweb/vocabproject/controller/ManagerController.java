package com.cdweb.vocabproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/home") // localhost:8080/manager/home
public class ManagerController {

    @GetMapping(value = {"/", ""})
    public String homeManager(Model model) {
        return "manager-home";
    }


}
