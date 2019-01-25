package com.bjornspetprojects.resttraining.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@ApiModel(description = "All details about the user.")
@Entity
@Getter
@Setter
public class User {

    public User(){
        posts = new ArrayList<>();
    }

    public User(Long id, @Size(min = 2, message = "Name should have at least 2 characters") String name, @Past(message = "Birthdate should be in the past") LocalDate birthDate, List<Post> posts) {
        this.posts = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = posts;
    }
    public User(@Size(min = 2, message = "Name should have at least 2 characters") String name, @Past(message = "Birthdate should be in the past") LocalDate birthDate) {
        this.posts = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = posts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(notes = "Name should have at least 2 characters")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @ApiModelProperty(notes = "Birthdate should always be in the past")
    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    public User addPost(Post newPost){
        newPost.setUser(this);
        System.out.println("Posts "+this.posts);
        this.posts.add(newPost);
        return this;
    }

}
