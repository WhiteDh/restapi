package com.goit.restapi.notes;

import com.goit.restapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Note createNote(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }

        if (note.getTitle().length() < 1 || note.getTitle().length() > 100) {
            throw new IllegalArgumentException("title should be between 1 and 100 characters");
        }
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("content cannot be null or empty");
        }
        if (note.getContent() != null && note.getContent().length() > 500) {
            throw new IllegalArgumentException("content should be up to 500 characters");
        }


        return noteRepository.save(note);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
    }

    public Note updateNote(Long id, Note noteDetails) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
        if (noteDetails.getTitle() == null || noteDetails.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }

        if (noteDetails.getTitle().length() < 1 || noteDetails.getTitle().length() > 100) {
            throw new IllegalArgumentException("title should be between 1 and 100 characters");
        }
        if (noteDetails.getContent() == null || noteDetails.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("content cannot be null or empty");
        }
        if (noteDetails.getContent() != null && noteDetails.getContent().length() > 500) {
            throw new IllegalArgumentException("content should be up to 500 characters");
        }
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
        noteRepository.delete(note);
    }
}
