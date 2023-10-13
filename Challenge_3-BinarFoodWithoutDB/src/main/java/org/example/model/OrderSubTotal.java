package org.example.model;

import java.util.Optional;

public class OrderSubTotal extends OrderItem {
    private Integer subTotal;

    public Integer getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public OrderSubTotal(Integer idMenu, String menuName, Integer menuPrice, Integer quantity, Integer idNotes, String notes, Integer subTotal) {
        super(idMenu, menuName, menuPrice, quantity, idNotes, notes);
        this.subTotal = subTotal;
    }
}
