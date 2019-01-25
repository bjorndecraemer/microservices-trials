package com.bjornspetprojects.resttraining.services;

import com.bjornspetprojects.resttraining.beans.Post;
import com.bjornspetprojects.resttraining.beans.User;
import com.bjornspetprojects.resttraining.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserJpaService implements com.bjornspetprojects.resttraining.services.UserService {
    @Override
    public User saveNewUser(User user) {
        if(user.getPosts() != null && !user.getPosts().isEmpty()){
            for (Post post : user.getPosts()) {
                post.setUser(user);
            }
        }
        return userRepository.save(user);
    }

    private final UserRepository userRepository;

    public UserJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User deleteById(Long id) {
        Optional<User> toDeleteUserOptional = userRepository.findById(id);
        if(toDeleteUserOptional.isPresent()) {
            userRepository.deleteById(id);
            return toDeleteUserOptional.get();
        }
        return null;
    }
}
