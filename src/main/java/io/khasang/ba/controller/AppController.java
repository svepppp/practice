package io.khasang.ba.controller;

import io.khasang.ba.Message;
import io.khasang.ba.service.CreateTable;
import io.khasang.ba.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    private Message message;
    @Autowired
    private CreateTable createTable;

    @Qualifier("myServiceImpl")
    @Autowired
    private MyService myService;

    // localhost:8080/
    @RequestMapping("/")
    public String getHelloPage(Model model){
        model.addAttribute("name", myService.getName());
        return "home";
    }

    // localhost:8080/name/asdasd
    @RequestMapping("/name/{name}")
    public String getName(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable(Model model) {
        model.addAttribute("status", createTable.getTableCreationStatus());
        return "create";
    }
}
