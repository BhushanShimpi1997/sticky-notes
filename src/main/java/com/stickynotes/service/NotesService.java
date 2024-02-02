package com.stickynotes.service;

import com.stickynotes.entity.Notes;
import com.stickynotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotesService {
    @Autowired
    private NotesRepository repository;

    public void saveNotes(Notes note){
        note.setCreatedAt(LocalDateTime.now());
        final Notes save = repository.save(note);
    }

    public List<Notes> getAllNotes(){
        return repository.getNotesWithFlagTrue();
    }

    public Notes editNotes(Integer id){
        final Notes notes = repository.findById(id).get();
        return notes;
    }

    public void updateNote(Notes note,Integer id){
        final Notes exNote = repository.findById(id).get();
        exNote.setTitle(note.getTitle());
        exNote.setDescription(note.getDescription());
        exNote.setUpdatedAt(LocalDateTime.now());
        repository.save(exNote);
    }

    public void deleteNote(Integer id){
        final Notes notes = repository.findById(id).get();
        notes.setFlag(false);
        repository.save(notes);
    }
}
