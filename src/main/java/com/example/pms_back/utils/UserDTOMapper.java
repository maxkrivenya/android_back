package com.example.pms_back.utils;

import com.example.pms_back.model.User;
import com.example.pms_back.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDTOMapper{

    public UserDTO wrap(User user){
        return new UserDTO(
                user.getName(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public User unwrap(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        return user;
    }
}