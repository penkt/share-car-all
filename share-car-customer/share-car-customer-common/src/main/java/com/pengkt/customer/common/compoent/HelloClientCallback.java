package com.pengkt.customer.common.compoent;

import org.springframework.stereotype.Component;

@Component
public class HelloClientCallback implements HelloClient{
    @Override
    public String hello(String name) {
        return "error";
    }

    @Override
    public String home(String name) {
        return "error";
    }
}
