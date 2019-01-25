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

        Post post1 = new Post("Went to the market!");
        System.out.println(post1.getId());
        Post post2 = new Post("Went to school!");
        Post post3 = new Post("Went to bed!");

//      User user1 = User.builder().name("John Doe").birthDate(LocalDate.of(2016,2,1)).build();
        User user1 = new User("John Doe",LocalDate.of(2016,2,1));
        User user2 = new User("Jimi Hendrix",LocalDate.of(2011,1,1));
        User user3 = new User("John Doe",LocalDate.of(2016,2,1));
        user1.addPost(post1);
        user1.addPost(post2);
        user2.addPost(post3);
        userService.saveNewUser(user1);
        userService.saveNewUser(user2);
        userService.saveNewUser(user3);
    }
}
