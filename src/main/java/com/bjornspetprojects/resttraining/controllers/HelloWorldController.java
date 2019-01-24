package com.bjornspetprojects.resttraining.controllers;

import com.bjornspetprojects.resttraining.beans.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world-bean/path-variable/{message}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String message){
        return new HelloWorldBean(String.format("Hello world, %s",message));
    }
}
