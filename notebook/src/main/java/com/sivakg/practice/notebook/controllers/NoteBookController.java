package com.sivakg.practice.notebook.controllers;


import com.sivakg.practice.notebook.entities.NoteBook;
import com.sivakg.practice.notebook.entities.User;
import com.sivakg.practice.notebook.services.NoteBookService;
import com.sivakg.practice.notebook.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note-book")
public class NoteBookController {

    @Autowired
    private NoteBookService noteBookService;

    @PostMapping
    public NoteBook create(@Valid @RequestBody NoteBook noteBook) {

        return noteBookService.create(noteBook);

    }
    @GetMapping
    public List<NoteBook> getAll(){
        return noteBookService.getAll();
    }


    @GetMapping("/{id}")
    public NoteBook getById(@PathVariable int id){
        return noteBookService.getById(id);
    }



    @PutMapping("/{id}")
    public NoteBook updateById(@PathVariable int id,@RequestBody NoteBook noteBook){
            return noteBookService.update(id, noteBook);

    }
    @DeleteMapping("/{id}")
    public void deleterById(@PathVariable int id){
        noteBookService.deleteById(id);
    }




}
