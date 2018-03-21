package com.pengkt.customer.common.compoent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "server",fallback = HelloClientCallback.class)
public interface HelloClient {

    @RequestMapping(method = {RequestMethod.POST},value = "/hello/index")
    String hello(@RequestParam(value = "name")String name);

    @RequestMapping(method = {RequestMethod.POST},value = "/hello/")
    String home(@RequestParam(value = "name")String name);
}
