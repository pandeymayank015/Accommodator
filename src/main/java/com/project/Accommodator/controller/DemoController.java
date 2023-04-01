package com.project.Accommodator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/testing")
public class DemoController {

    @GetMapping("/in")
    public void testing(){
        System.out.println("in test");
    }


}
