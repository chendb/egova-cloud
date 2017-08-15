package com.egova.oauth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hbche on 2017/7/23.
 */
@RestController
public class HomeController {

    @RequestMapping("/service2")
    public String home() {
        return "resource2 service";
    }

    @RequestMapping("/service22")
    public String home2() {
        return "Hello World";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody MultiValueMap<String, String> map) {
        return "OK";
    }

}
