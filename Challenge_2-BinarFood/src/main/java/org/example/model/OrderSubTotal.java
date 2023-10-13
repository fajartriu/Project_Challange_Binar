package org.example.model;

public class OrderSubTotal extends OrderItem {
    private Integer subTotal;

    public Integer getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public OrderSubTotal(Integer idMenu, String menuName, Integer menuPrice, Integer quantity, Integer subTotal) {
        super(idMenu, menuName, menuPrice, quantity);
        this.subTotal = subTotal;
    }
}
