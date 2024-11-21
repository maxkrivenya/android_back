package com.example.pms_back.repository;

import com.example.pms_back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAll();

    User getUserByName(String name);
    User getUserByEmail(String email);

    boolean existsById(String id);
    Boolean existsByName(String name   );
    Boolean existsByEmail(String email);

    void deleteUserByName(String name);

    @Override
    User save(User user);

}