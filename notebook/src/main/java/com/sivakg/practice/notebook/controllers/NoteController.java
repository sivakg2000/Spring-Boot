package com.sivakg.practice.notebook.controllers;


import com.sivakg.practice.notebook.entities.Note;
import com.sivakg.practice.notebook.entities.NoteBook;
import com.sivakg.practice.notebook.services.NoteBookService;
import com.sivakg.practice.notebook.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note create(@Valid @RequestBody Note note) {
        return noteService.create(note);
    }
    @GetMapping
    public List<Note> getAll(){
        return noteService.getAll();
    }


    @GetMapping("/{id}")
    public Note getById(@PathVariable int id){
        return noteService.getById(id);
    }



    @PutMapping("/{id}")
    public Note updateById(@PathVariable int id,@RequestBody Note note){
            return noteService.update(id, note);

    }
    @DeleteMapping("/{id}")
    public void deleterById(@PathVariable int id){
        noteService.deleteById(id);
    }

  


}
