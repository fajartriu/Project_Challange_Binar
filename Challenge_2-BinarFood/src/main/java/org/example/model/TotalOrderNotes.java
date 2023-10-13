package org.example.model;

public class TotalOrderNotes {
    private String name;
    private String notes;
    private Integer totalNotes;

    public TotalOrderNotes(String name, String notes, Integer totalNotes) {
        this.name = name;
        this.notes = notes;
        this.totalNotes = totalNotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getTotalNotes() {
        return totalNotes;
    }

    public void setTotalNotes(Integer totalNotes) {
        this.totalNotes = totalNotes;
    }
}
