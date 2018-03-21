package com.pengkt.customer.biz.controller;

import com.pengkt.customer.biz.exception.GenerBizException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(@RequestParam(value = "name")String name){
        return "Hello "+name;
    }
    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String home(@RequestParam(value = "name",required = false)String name)throws GenerBizException {
        throw new GenerBizException("asss");
    }
}
