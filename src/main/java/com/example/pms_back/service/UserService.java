package com.example.pms_back.service;

import com.example.pms_back.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByEmail(String email);
    User getUserByName(String name);

    void deleteUserByName(String name);

    User save(User user);

    boolean userExistsByName(String name);

}