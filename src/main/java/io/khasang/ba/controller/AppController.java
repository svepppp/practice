package io.khasang.ba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping("/")
    public String getHelloPage(Model model){
        model.addAttribute("name", "first Spring application!");
        return "home";
    }

    @RequestMapping("/name/{name}")
    public String getName(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
