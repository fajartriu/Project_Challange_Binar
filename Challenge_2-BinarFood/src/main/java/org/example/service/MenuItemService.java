package org.example.service;

import java.util.List;
import org.example.model.Data;
import org.example.model.MenuItem;

public class MenuItemService {
    public void initiateData(){
        Data.menuItems.add(new MenuItem(1,"Nasi Goreng", 15000));
        Data.menuItems.add(new MenuItem(2,"Mie Goreng", 13000));
        Data.menuItems.add(new MenuItem(3,"Nasi + Ayam", 18000));
        Data.menuItems.add(new MenuItem(4,"Es Teh Manis", 3000));
        Data.menuItems.add(new MenuItem(5,"Es Jeruk", 5000));
    }

    public List<MenuItem> getMenuItems(){
        return Data.menuItems;
    }

    public Integer getMenuId(String name){
        for (MenuItem menuItem: Data.menuItems){
            if (menuItem.getMenuName().equals(name)){
                return menuItem.getIdMenu();
            }
        }
        return null;
    }

    public Integer getMenuPrice(String name){
        for (MenuItem menuItem: Data.menuItems){
            if (menuItem.getMenuName().equals(name)){
                return menuItem.getMenuPrice();
            }
        }
        return null;
    }
}
