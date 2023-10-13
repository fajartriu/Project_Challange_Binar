package org.example.service;

import org.example.model.Data;
import org.example.model.Notes;

import java.util.List;
import java.util.Optional;

public class NotesService {
    public void initiateNotesMakanan(){
        Data.notesMakanan.add(Optional.of(new Notes(1, "Pedas")));
        Data.notesMakanan.add(Optional.of(new Notes(2, "Sedang")));
        Data.notesMakanan.add(Optional.of(new Notes(3, "Biasa")));
    }

    public void initiateNotesMinuman(){
        Data.notesMinuman.add(Optional.of(new Notes(1, "Panas")));
        Data.notesMinuman.add(Optional.of(new Notes(2, "Dingin")));
    }

    public List<Optional<Notes>> getNotesMakanan(){
        return Data.notesMakanan;
    }
    public List<Optional<Notes>> getNotesMinuman(){
        return Data.notesMinuman;
    }


}
