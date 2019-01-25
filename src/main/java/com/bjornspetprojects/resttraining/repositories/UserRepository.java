package com.bjornspetprojects.resttraining.repositories;

import com.bjornspetprojects.resttraining.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
