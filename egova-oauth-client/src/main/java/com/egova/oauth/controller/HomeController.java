package com.egova.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hbche on 2017/7/23.
 */
@Controller
public class HomeController {
    @RequestMapping("/dashboard/login")
    public String dashboard() {
        return "redirect:/#/";
    }
}
