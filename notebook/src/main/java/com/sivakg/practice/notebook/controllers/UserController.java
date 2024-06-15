package com.sivakg.practice.notebook.controllers;


import com.sivakg.practice.notebook.entities.User;
import com.sivakg.practice.notebook.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //@Autowired
    //private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        if(userService.getUserByEmail(user.getEmail())==null) {
            //String hashedPassword = passwordEncoder.encode(user.getPassword());
            //user.setPassword(hashedPassword);
            User newUser = userService.createUser(user);
            return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        }else{
            throw new DuplicateKeyException("Email Already Exist.");
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }


    @GetMapping("/by-email/{email}")
    public User getUserByEmail(@PathVariable String  email){
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id,@RequestBody User user){

        if(userService.getUserByEmail(user.getEmail())==null) {
            //String hashedPassword = passwordEncoder.encode(user.getPassword());
            //user.setPassword(hashedPassword);
            userService.updateUser(id, user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }else{
            throw new DuplicateKeyException("Email Already Exist.");
        }
    }


    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id){
         userService.deleteUserById(id);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getEmail(),user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }



}
