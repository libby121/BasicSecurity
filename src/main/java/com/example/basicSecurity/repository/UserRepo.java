package com.example.basicSecurity.repository;

import com.example.basicSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    User findByName(String name);


    User findById(int userId);
}
