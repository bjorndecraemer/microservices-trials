package com.bjornspetprojects.resttraining.bootstrap;

import com.bjornspetprojects.resttraining.beans.Post;
import com.bjornspetprojects.resttraining.beans.User;
import com.bjornspetprojects.resttraining.repositories.PostRepository;
import com.bjornspetprojects.resttraining.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StartupTestData implements CommandLineRunner {

    private final UserService userService;
    private final PostRepository postRepository;

    public StartupTestData(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = userService.saveNewUser(User.builder().name("John Doe").birthDate(LocalDate.of(2016,2,1)).build());
        User user2 = userService.saveNewUser(User.builder().name("Mary Poppins").birthDate(LocalDate.of(2014,1,12)).build());
        User user3 = userService.saveNewUser(User.builder().name("Jimi Hendrix").birthDate(LocalDate.of(1948,2,1)).build());
        User user4 = userService.saveNewUser(User.builder().name("Stevie Ray Vaughan").birthDate(LocalDate.of(1955,9,24)).build());

        Post post1 = postRepository.save(new Post("Went to the market!",user1));
        Post post2 = postRepository.save(new Post("Went to school!",user2));
        Post post3 = postRepository.save(new Post("Went to bed!",user2));
    }
}
