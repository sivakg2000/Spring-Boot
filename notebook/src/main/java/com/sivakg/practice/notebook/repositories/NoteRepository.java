package com.sivakg.practice.notebook.repositories;

import com.sivakg.practice.notebook.entities.Note;
import com.sivakg.practice.notebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
    Optional<List<Note>> findAllByUserid(int userid);
}
