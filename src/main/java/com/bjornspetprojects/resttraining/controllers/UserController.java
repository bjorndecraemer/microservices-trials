package com.bjornspetprojects.resttraining.controllers;

import com.bjornspetprojects.resttraining.beans.User;
import com.bjornspetprojects.resttraining.exception.UserNotFoundException;
import com.bjornspetprojects.resttraining.services.UserDaoService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public ResponseEntity<Object> findAll (){
        List<User> allUsers = userDaoService.findAll();

        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Long id)throws UserNotFoundException{
        User foundUser = userDaoService.findOne(id);
        if(foundUser == null) {
            throw new UserNotFoundException("id - "+id);
        }
        Resource<User> resource = new Resource<>(foundUser);
        resource.add(linkTo(methodOn(this.getClass()).findAll()).withRel("all-users"));
        return ResponseEntity.ok().body(resource);
    }

    @DeleteMapping("/users/{id}")
    public void deleteOne(@PathVariable Long id)throws UserNotFoundException{
        User deletedUser = userDaoService.deleteById(id);
        if(deletedUser == null) {
            throw new UserNotFoundException("id - "+id);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser (@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
