package com.bjornspetprojects.resttraining.services;

import com.bjornspetprojects.resttraining.beans.User;

import java.util.List;

public interface UserService {
    public User findById(Long id);
    public List<User> findAll();
    public User deleteById(Long id);
    public User saveNewUser(User user);
}
