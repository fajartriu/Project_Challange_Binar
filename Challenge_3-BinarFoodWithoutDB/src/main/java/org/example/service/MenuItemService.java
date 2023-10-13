package org.example.service;

import java.util.List;
import java.util.Optional;

import org.example.model.Data;
import org.example.model.MenuItem;

public class MenuItemService {
    public List<Optional<MenuItem>> initiateMenuFoods(){
        Data.menuFoods.add(Optional.of(new MenuItem(1,"Nasi Goreng", 15000)));
        Data.menuFoods.add(Optional.of(new MenuItem(2,"Mie Goreng", 13000)));
        Data.menuFoods.add(Optional.of(new MenuItem(3,"Nasi + Ayam", 18000)));
        return Data.menuFoods;
    }

    public List<Optional<MenuItem>> initiateMenuDrinks(){
        Data.menuDrinks.add(Optional.of(new MenuItem(4,"Teh Manis", 3000)));
        Data.menuDrinks.add(Optional.of(new MenuItem(5,"Jeruk", 5000)));
        return Data.menuDrinks;
    }

    public void initiateMenu(){
        initiateMenuFoods().forEach(menuItem -> menuItem.ifPresent(item -> Data.menuItems.add(Optional.of(new MenuItem(item.getIdMenu(), item.getMenuName(), item.getMenuPrice())))));
        initiateMenuDrinks().forEach(menuItem -> menuItem.ifPresent(item -> Data.menuItems.add(Optional.of(new MenuItem(item.getIdMenu(), item.getMenuName(), item.getMenuPrice())))));
    }

    public List<Optional<MenuItem>> getMenuItems(){
        return Data.menuItems;
    }

    public Integer getMenuId(String name){
        return Data.menuItems.stream().filter(Optional::isPresent).filter(menuItem -> menuItem.get().getMenuName().equals(name)).findFirst().map(menuItem -> menuItem.get().getIdMenu()).orElse(null);
    }

    public Integer getMenuPrice(String name){
        return Data.menuItems.stream().filter(Optional::isPresent).filter(menuItem -> menuItem.get().getMenuName().equals(name)).findFirst().map(menuItem -> menuItem.get().getMenuPrice()).orElse(null);
    }
}
