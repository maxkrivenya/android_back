package com.example.pms_back.controller;

import com.example.pms_back.model.User;
import com.example.pms_back.model.dto.UserDTO;
import com.example.pms_back.utils.UserDTOMapper;
import com.example.pms_back.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/user")
@Transactional
@Validated
public class UserController {
    private final UserServiceImpl userService;
    private final UserDTOMapper userDTOMapper;

    public UserController(UserServiceImpl userService, UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable("name") String name) {
        User user = userService.getUserByName(name);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userDTOMapper.wrap(user) , HttpStatus.OK);
        }
    }
    
    @PutMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

        if(!userService.userExistsByName(userDTO.name())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userDTOMapper.unwrap(userDTO);

        return new ResponseEntity<>(userDTOMapper.wrap(userService.save(user)), HttpStatus.OK);
    }

    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User user = userDTOMapper.unwrap(userDTO);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<String> deleteUserById(@PathVariable(name="name") String name) {
        userService.deleteUserByName(name);
        return ResponseEntity.ok("deleted user " + name);
    }


}