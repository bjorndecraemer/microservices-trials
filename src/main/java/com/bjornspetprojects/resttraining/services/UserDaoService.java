package com.bjornspetprojects.resttraining.services;

import com.bjornspetprojects.resttraining.beans.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(User.builder().id(0l).name("John Doe").birthDate(LocalDate.now()).build());
        users.add(User.builder().id(1l).name("Marie Curie").birthDate(LocalDate.now()).build());
        users.add(User.builder().id(2l).name("Alan Turing").birthDate(LocalDate.now()).build());
        users.add(User.builder().id(3l).name("Wiley Coyote").birthDate(LocalDate.now()).build());
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null) {
            user.setId((long)users.size());
            users.add(user);
        }
        else{
            users.set(user.getId().intValue(),user);
        }
        return user;
    }

    public User findOne(Long id){
        Optional<User> foundUser = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
        if(foundUser.isPresent()){
            return foundUser.get();
        }
        else{
            return null;
        }
    }
    public User deleteById(Long id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId().equals(id)){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
