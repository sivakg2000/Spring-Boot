package com.sivakg.practice.notebook.controllers;


import com.sivakg.practice.notebook.entities.Note;
import com.sivakg.practice.notebook.entities.NoteBook;
import com.sivakg.practice.notebook.entities.User;
import com.sivakg.practice.notebook.services.NoteBookService;
import com.sivakg.practice.notebook.services.NoteService;
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

    @Autowired
    private NoteBookService noteBookService;

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        if(userService.getByEmail(user.getEmail())==null) {
            //String hashedPassword = passwordEncoder.encode(user.getPassword());
            //user.setPassword(hashedPassword);
            User newUser = userService.create(user);
            return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        }else{
            throw new DuplicateKeyException("Email Already Exist.");
        }
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }


    @GetMapping("/{id}")
    public User getById(@PathVariable int id){
        return userService.getById(id);
    }


    @GetMapping("/by-email/{email}")
    public User getByEmail(@PathVariable String  email){
        return userService.getByEmail(email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@PathVariable int id,@RequestBody User user){

        if(userService.getByEmail(user.getEmail())==null) {
            //String hashedPassword = passwordEncoder.encode(user.getPassword());
            //user.setPassword(hashedPassword);
            userService.update(id, user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }else{
            throw new DuplicateKeyException("Email Already Exist.");
        }
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
         userService.deleteById(id);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getEmail(),user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/{userid}/note-book")
    public NoteBook getNoteBookByUserid(@PathVariable int userid){
        return noteBookService.getById(userid);
    }


    @GetMapping("/{userid}/note-book/notes")
    public List<Note> getNoteBookNotesByUserid(@PathVariable int userid){
        NoteBook noteBook= noteBookService.getById(userid);;
        return noteService.getAllByNoteBook(noteBook);
    }



}
