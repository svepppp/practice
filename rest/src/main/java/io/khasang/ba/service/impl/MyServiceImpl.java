package io.khasang.ba.service.impl;

import io.khasang.ba.service.MyService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyServiceImpl implements MyService {
    @Override
    public String getName() {
        return "bean from interface";
    }

    @PostConstruct
    public void init() {
        System.err.println("Before init");
    }

    @PreDestroy
    public void cleanUp() {
        System.err.println("Before destroy");
    }
}
