
package com.terry.es.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = "/home")
    public String index() {
        System.out.println(111);
        return "index";
    }
}
