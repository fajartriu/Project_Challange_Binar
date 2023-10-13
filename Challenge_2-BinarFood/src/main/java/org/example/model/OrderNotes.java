package org.example.model;

public class OrderNotes extends Note{
    private String name;

    public OrderNotes(Integer idNotes, String notes, String name) {
        super(idNotes, notes);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
