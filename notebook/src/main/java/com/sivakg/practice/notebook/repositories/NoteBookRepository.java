package com.sivakg.practice.notebook.repositories;

import com.sivakg.practice.notebook.entities.NoteBook;
import com.sivakg.practice.notebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook,Integer> {

}
