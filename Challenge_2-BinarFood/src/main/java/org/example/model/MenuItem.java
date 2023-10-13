package org.example.model;

public class MenuItem {
    private Integer idMenu;
    private String menuName;
    private Integer menuPrice;

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuPrice() {
        return this.menuPrice;
    }

    public void setMenuPrice(Integer menuPrice) {
        this.menuPrice = menuPrice;
    }

    public MenuItem() {
    }

    public MenuItem(Integer idMenu, String menuName, Integer menuPrice) {
        this.idMenu = idMenu;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}

