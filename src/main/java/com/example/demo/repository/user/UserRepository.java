package com.example.demo.repository.user;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : bidok
 * @created : 12.05.2021, среда
 * @className : UserRepository
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
