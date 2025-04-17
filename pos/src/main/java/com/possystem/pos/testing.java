package com.possystem.pos;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class testing {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
