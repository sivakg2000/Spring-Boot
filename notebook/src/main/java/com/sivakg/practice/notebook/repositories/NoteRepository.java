package com.sivakg.practice.notebook.repositories;

import com.sivakg.practice.notebook.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
  
}
