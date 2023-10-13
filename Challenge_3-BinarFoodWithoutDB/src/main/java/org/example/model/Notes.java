package org.example.model;

import java.util.Optional;

public class Notes {
    private Integer idNotes;
    private String notes;

    public Notes(Integer idNotes, String notes) {
        this.idNotes = idNotes;
        this.notes = notes;
    }

    public Notes() {
    }

    public Integer getIdNotes() {
        return idNotes;
    }

    public void setIdNotes(Integer idNotes) {
        this.idNotes = idNotes;
    }

    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }
}
