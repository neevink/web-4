package com.neevin.lab4.controllers;

import com.neevin.lab4.DTO.UserDTO;
import com.neevin.lab4.auth.JWTUtils;
import com.neevin.lab4.models.RoleEnum;
import com.neevin.lab4.models.User;
import com.neevin.lab4.repositories.RoleRepository;
import com.neevin.lab4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final JWTUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, JWTUtils jwtUtils) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            if (userService.findByUsername(userDTO.getUsername()) != null) {
                throw new IllegalArgumentException();
            }
            userService.addUser(new User(
                    userDTO.getUsername(),
                    passwordEncoder.encode(userDTO.getPassword()),
                    roleRepository.findByName(RoleEnum.USER.getName())
            ));
            return ResponseEntity.ok().body("New user with username " + userDTO.getUsername() + " created!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("This username " + userDTO.getUsername() + " is already exist!");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            User userFromDB = userService.findByUsername(userDTO.getUsername());
            if (userFromDB == null) {
                throw new IllegalArgumentException();
            } else if (!passwordEncoder.matches(userDTO.getPassword(), userFromDB.getPassword())) {
                throw new IllegalAccessException();
            } else {
                String token = jwtUtils.generateToken(userDTO.getUsername());
                return ResponseEntity.ok().body(token);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            return ResponseEntity.badRequest().body("Incorrect username or password!");
        }
    }
}
