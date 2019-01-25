package com.bjornspetprojects.resttraining.repositories;

import com.bjornspetprojects.resttraining.beans.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
