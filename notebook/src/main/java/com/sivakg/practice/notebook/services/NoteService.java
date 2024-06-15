package com.sivakg.practice.notebook.services;

import com.sivakg.practice.notebook.entities.Note;
import com.sivakg.practice.notebook.entities.NoteBook;
import com.sivakg.practice.notebook.repositories.NoteBookRepository;
import com.sivakg.practice.notebook.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Note create(Note note){
        return noteRepository.save(note);
    }

    public List<Note> getAll(){
        return noteRepository.findAll();
    }



    public Note getById(int id){
        Optional<Note>  noteBook=noteRepository.findById(id);
        return noteBook.orElse(null);
    }

    public List<Note> getAllByUserId(int userid){
        return noteRepository.findAllByUserid(userid).orElse(null);
    }

    public Note update(int id,Note note){
        Optional<Note>  oldNote=noteRepository.findById(note.getId());
        if(oldNote.isPresent() && oldNote.get().getId()==id){
            noteRepository.save(note);
            return  note;
        }else{
            return null;
        }

    }
    public void deleteById(int id){
        noteRepository.deleteById(id);
    }


}
