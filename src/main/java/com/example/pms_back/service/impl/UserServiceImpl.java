package com.example.pms_back.service.impl;

import com.example.pms_back.model.Stats;
import com.example.pms_back.model.User;
import com.example.pms_back.repository.StatsRepository;
import com.example.pms_back.utils.UserDTOMapper;
import com.example.pms_back.service.UserService;
import com.example.pms_back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StatsRepository statsRepository;


    public UserServiceImpl(UserRepository userRepository, UserDTOMapper userDTOMapper, StatsRepository statsRepository) {
        this.userRepository = userRepository;
        this.statsRepository = statsRepository;
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

    public Boolean existsById(String id){
        return userRepository.existsById(id);
    }

    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Boolean existsByName(String name){
        return userRepository.existsByName(name);
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