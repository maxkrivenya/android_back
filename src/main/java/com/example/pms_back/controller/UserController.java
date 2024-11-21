package com.example.pms_back.controller;

import com.example.pms_back.model.Stats;
import com.example.pms_back.model.User;
import com.example.pms_back.model.dto.StatsDTO;
import com.example.pms_back.model.dto.UserDTO;
import com.example.pms_back.repository.StatsRepository;
import com.example.pms_back.service.impl.StatsServiceImpl;
import com.example.pms_back.utils.StatsDTOMapper;
import com.example.pms_back.utils.UserDTOMapper;
import com.example.pms_back.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/user")
@Transactional
@Validated
public class UserController {
    private final UserServiceImpl userService;
    private final StatsServiceImpl statsService;
    private final UserDTOMapper userDTOMapper;
    private final StatsDTOMapper statsDTOMapper;
    private final StatsRepository statsRepository;

    public UserController(UserServiceImpl userService, StatsServiceImpl statsService, UserDTOMapper userDTOMapper, StatsDTOMapper statsDTOMapper, StatsRepository statsRepository) {
        this.userService = userService;
        this.statsService = statsService;
        this.userDTOMapper = userDTOMapper;
        this.statsDTOMapper = statsDTOMapper;
        this.statsRepository = statsRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable("id") String id) {
        User user = userService.getUserByName(id);
        if (user == null) {
            user = userService.getUserByEmail(id);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userDTOMapper.wrap(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDTOMapper.wrap(user) , HttpStatus.OK);
        }
    }
    @GetMapping("/exists/name/{name}")
    public ResponseEntity<Boolean> existsByName(@PathVariable("name") String name) {
        boolean result = userService.existsByName(name);
        if (!result) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable("email") String email) {
        boolean result = userService.existsByEmail(email);
        if (!result) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/stats/{id}")
    public ResponseEntity<List<StatsDTO>> getStatsByUserId(@PathVariable("id") String id) {
        boolean userExists = userService.existsById(id);
        if (!userExists){return ResponseEntity.notFound().build();}
        List<Stats> stats = statsService.findByUserId(id);
        List<StatsDTO> dtos = stats.stream().map(s -> { statsDTOMapper.wrap(s); return statsDTOMapper.wrap(s); } ).toList();
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping("/auth/")
    public ResponseEntity<Boolean> auth(@RequestBody UserDTO userDTO) {
        User user = userService.getUserByName(userDTO.name());
        if (user == null) {
            user = userService.getUserByEmail(userDTO.name());
        }
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userDTO.password().equals(user.getPassword())) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
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
        boolean existsByName = userService.existsByName(userDTO.name());
        boolean existsByEmail = userService.existsByEmail(userDTO.email());
        if (!existsByEmail && !existsByName) {
            User user = userDTOMapper.unwrap(userDTO);
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<String> deleteUserById(@PathVariable(name="name") String name) {
        userService.deleteUserByName(name);
        return ResponseEntity.ok("deleted user " + name);
    }

    @GetMapping(path="/stats/{login}/{date}")
    public ResponseEntity<List<StatsDTO>> getStatsByUserIdAndDateAfter(@PathVariable(name="login") String login,
                                                                       @PathVariable(name="date") ZonedDateTime date
    ){

        User user = userService.getUserByName(login);
        if (user == null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        List<Stats> stats = statsService.findByUserIdAndDateAfter(user.getId(), date);
        List<StatsDTO> dtos = stats.stream().map(statsDTOMapper::wrap).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}