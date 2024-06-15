package com.sivakg.practice.notebook.services;

import com.sivakg.practice.notebook.entities.NoteBook;
import com.sivakg.practice.notebook.entities.User;
import com.sivakg.practice.notebook.repositories.NoteBookRepository;
import com.sivakg.practice.notebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NoteBookService {
    @Autowired
    private NoteBookRepository  noteBookRepository;

    public NoteBook create(NoteBook noteBook){
        return noteBookRepository.save(noteBook);
    }

    public List<NoteBook> getAll(){
        return noteBookRepository.findAll();
    }



    public NoteBook getById(int id){
        Optional<NoteBook>  noteBook=noteBookRepository.findById(id);
        return noteBook.orElse(null);
    }

    public NoteBook update(int id,NoteBook noteBook){
        Optional<NoteBook>  oldNoteBook=noteBookRepository.findById(noteBook.getId());
        if(oldNoteBook.isPresent() && oldNoteBook.get().getId()==id){
            noteBookRepository.save(noteBook);
            return  noteBook;
        }else{
            return null;
        }

    }
    public void deleteById(int id){
        noteBookRepository.deleteById(id);
    }


}
