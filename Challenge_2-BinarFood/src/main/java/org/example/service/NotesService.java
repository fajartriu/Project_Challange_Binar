package org.example.service;

import org.example.model.Data;
import org.example.model.Note;

import java.util.List;

public class NotesService {
    public void initiateNotesMakanan(){
        Data.notesMakanan.add(new Note(1, "Pedas"));
        Data.notesMakanan.add(new Note(2, "Sedang"));
        Data.notesMakanan.add(new Note(3, "Biasa"));
    }

    public void initiateNotesMinuman(){
        Data.notesMinuman.add(new Note(1, "Panas"));
        Data.notesMinuman.add(new Note(2, "Dingin"));
    }

    public List<Note> getNotesMakanan(){
        return Data.notesMakanan;
    }
    public List<Note> getNotesMinuman(){
        return Data.notesMinuman;
    }

}
