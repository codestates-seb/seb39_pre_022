package com.Team22.preproject.StackOverFlow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @GetMapping("/")
    public String helloWorld(){
        return "To-do Application!!!!";
    }
}
