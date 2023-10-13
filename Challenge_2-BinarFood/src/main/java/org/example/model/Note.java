package org.example.model;

public class Note{
    private Integer idNotes;
    private String notes;

    public Note(Integer idNotes, String notes) {
        this.idNotes = idNotes;
        this.notes = notes;
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
