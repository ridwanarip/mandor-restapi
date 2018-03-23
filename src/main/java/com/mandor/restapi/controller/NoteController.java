package com.mandor.restapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mandor.restapi.exeption.ResourceNotFoundException;
import com.mandor.restapi.model.Note;
import com.mandor.restapi.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;
	
	//get all Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}
	
	//create a new Note
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note);
	}
	
	//get single Note
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(
				() -> new ResourceNotFoundException("Note", "id", noteId));
	}
	
	//update a Note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId,
							@Valid @RequestBody Note noteUpdate) {
		Note note = noteRepository.findById(noteId).orElseThrow(
				() -> new ResourceNotFoundException("Note", "id", noteId));
		
		note.setTitle(noteUpdate.getTitle());
		note.setContent(noteUpdate.getContent());
		
		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}
	
	//delete a note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		Note note = noteRepository.findById(noteId).orElseThrow(
				() -> new ResourceNotFoundException("Note", "id", noteId));
		noteRepository.delete(note);
		
		return ResponseEntity.ok().build();
	}
}