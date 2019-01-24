package com.bjornspetprojects.resttraining.controllers;

import com.bjornspetprojects.resttraining.beans.User;
import com.bjornspetprojects.resttraining.exception.UserNotFoundException;
import com.bjornspetprojects.resttraining.services.UserDaoService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    private final MessageSource messageSource;
    private final UserDaoService userDaoService;

    public UserController(MessageSource messageSource, UserDaoService userDaoService) {
        this.messageSource = messageSource;
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public ResponseEntity<Object> findAll (){
        List<User> allUsers = userDaoService.findAll();

        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/users/{id}")
    public Resource<User> findOne(@PathVariable Long id)throws UserNotFoundException{
        User foundUser = userDaoService.findOne(id);
        if(foundUser == null) {
            throw new UserNotFoundException(messageSource.getMessage("error.user.not.found",null, LocaleContextHolder.getLocale())+"id - "+id);
        }
        Resource<User> resource = new Resource<>(foundUser);
        resource.add(linkTo(methodOn(this.getClass()).findAll()).withRel("all-users"));
        return resource;
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
