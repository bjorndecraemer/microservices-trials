package com.bjornspetprojects.resttraining.controllers;


import com.bjornspetprojects.resttraining.beans.Name;
import com.bjornspetprojects.resttraining.beans.PersonV1;
import com.bjornspetprojects.resttraining.beans.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    // Basic versioning

    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob"," Charlie"));
    }

    // Versioning with Params

    @GetMapping(value="/person/param", params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="person/param", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Bob"," Charlie"));
    }

    // Versioning with headers

    @GetMapping(value="/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headersV1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="person/header", headers = "X-API-VERSION=2")
    public PersonV2 headersV2(){
        return new PersonV2(new Name("Bob"," Charlie"));
    }

    // Versioning with produces

    @GetMapping(value="/person/produces", produces = "application/bjornspetprojects.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="person/produces", produces = "application/bjornspetprojects.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Bob"," Charlie"));
    }

}
