package com.mandor.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mandor.restapi.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}