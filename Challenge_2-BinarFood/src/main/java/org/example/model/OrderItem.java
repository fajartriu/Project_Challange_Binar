package org.example.model;

public class OrderItem extends MenuItem {
    private Integer quantity;

    public OrderItem(Integer idMenu, String menuName, Integer menuPrice, Integer quantity) {
        super(idMenu, menuName, menuPrice);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
