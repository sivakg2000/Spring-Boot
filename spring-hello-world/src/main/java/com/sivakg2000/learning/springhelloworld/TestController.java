package com.sivakg2000.learning.springhelloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TestController
{
    @RequestMapping("/test")
    public String  getSampleResponse(){
        return "Hello World";
    }
}
