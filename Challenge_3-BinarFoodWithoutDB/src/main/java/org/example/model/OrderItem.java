package org.example.model;

import java.util.Optional;

public class OrderItem extends MenuItem {
    private Integer quantity;
    private Integer idNotes;
    private String notes;


    public OrderItem(Integer idMenu, String menuName, Integer menuPrice, Integer quantity, Integer idNotes, String notes) {
        super(idMenu, menuName, menuPrice);
        this.quantity = quantity;
        this.idNotes = idNotes;
        this.notes = notes;
    }

    public OrderItem() {

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

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getConcatMenuNameNotes(){
        return getMenuName() + " " + getNotes();
    }


}
