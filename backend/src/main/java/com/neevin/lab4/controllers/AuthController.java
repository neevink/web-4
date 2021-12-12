package com.neevin.lab4.controllers;

import com.neevin.lab4.auth.JWTUtils;
import com.neevin.lab4.auth.JsonWebToken;
import com.neevin.lab4.models.User;
import com.neevin.lab4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    @CrossOrigin(allowCredentials = "true")
    private ResponseEntity<?> register(@Validated @RequestBody User user, HttpSession session) {
        if (!userService.saveUser(user)) {
            return ResponseEntity.badRequest().body("Name " + user.getUsername() + " is already used");
        }
        return ResponseEntity.ok().body(user.getUsername());
    }

    @PostMapping("/login")
    @CrossOrigin
    private ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
        try {
            User dbUser = (User) userService.loadUserByUsername(user.getUsername());
            if (!bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                throw new IllegalArgumentException();
            }

            String jwtToken = jwtUtils.generateJwtToken(user.getUsername());
            return ResponseEntity.ok(new JsonWebToken(user.getUsername(), jwtToken));

        } catch (UsernameNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Wrong username or/and password");
        }
    }
}
