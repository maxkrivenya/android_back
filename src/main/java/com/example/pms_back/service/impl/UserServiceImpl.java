package com.example.pms_back.service.impl;

import com.example.pms_back.model.User;
import com.example.pms_back.utils.UserDTOMapper;
import com.example.pms_back.service.UserService;
import com.example.pms_back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
    }

    public User getUserById(String name){
        User user = userRepository.getUserByName(name);
        return user;
    }

    public User getUserByName(String name){
        User user = userRepository.getUserByName(name);
        return user;
    }

    public User getUserByEmail(String email){
        User user = userRepository.getUserByEmail(email);
        return user;
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUserByName(String name){
        userRepository.deleteUserByName(name);
    }

    public User save(User user){
        User existing = getUserByName(user.getName());
        if(existing != null) {
            existing.fill(user);
            return userRepository.save(existing);
        }else{
            return  userRepository.save(user);
        }
    }

    public boolean userExistsByName(String name){
        return userRepository.getUserByName(name) != null;
    }
}