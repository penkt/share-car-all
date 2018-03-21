package com.pengkt.customer.common.controller;

import com.pengkt.customer.common.compoent.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCustomerController {
    @Autowired
    private HelloClient helloClient;

    @RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(String name){
        return helloClient.hello(name);
    }
    @RequestMapping(value = "/home",method = {RequestMethod.GET,RequestMethod.POST})
    public String home(String name){
        return helloClient.home(name);
    }
}
