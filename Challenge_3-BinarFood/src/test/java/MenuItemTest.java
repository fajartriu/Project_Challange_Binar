import org.example.model.Data;
import org.example.model.MenuItem;
import org.example.model.Notes;
import org.example.service.MenuItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {
    MenuItemService menuItemService = new MenuItemService();

    @BeforeAll
    static void initiateMenu() {
        MenuItem tempMenuFood1 = new MenuItem();
        tempMenuFood1.setIdMenu(1);
        tempMenuFood1.setMenuName("Nasi Goreng");
        tempMenuFood1.setMenuPrice(15000);
        Data.menuItems.add(Optional.of(tempMenuFood1));

        MenuItem tempMenuFood2 = new MenuItem();
        tempMenuFood2.setIdMenu(2);
        tempMenuFood2.setMenuName("Mie Goreng");
        tempMenuFood2.setMenuPrice(13000);
        Data.menuItems.add(Optional.of(tempMenuFood2));

        MenuItem tempMenuFood3 = new MenuItem();
        tempMenuFood3.setIdMenu(3);
        tempMenuFood3.setMenuName("Nasi + Ayam");
        tempMenuFood3.setMenuPrice(18000);
        Data.menuItems.add(Optional.of(tempMenuFood3));

        MenuItem tempMenuDrink4 = new MenuItem();
        tempMenuDrink4.setIdMenu(4);
        tempMenuDrink4.setMenuName("Teh Manis");
        tempMenuDrink4.setMenuPrice(3000);
        Data.menuItems.add(Optional.of(tempMenuDrink4));

        MenuItem tempMenuDrink5 = new MenuItem(5, "Jeruk", 5000);
        Data.menuItems.add(Optional.of(tempMenuDrink5));
    }
//    @Test
//    @DisplayName("Positive Test -> Cek List Initiate Menu Drinks")
//    void initiateMenuDrinkTest(){
//        MenuItem tempMenuDrink = new MenuItem();
//        List<MenuItem> listExpectedMenuDrink = new ArrayList<>();
//        tempMenuDrink.setIdMenu(4);
//        tempMenuDrink.setMenuName("Teh Manis");
//        tempMenuDrink.setMenuPrice(3000);
//        listExpectedMenuDrink.add(tempMenuDrink);
//        listExpectedMenuDrink.add(new MenuItem(5,"Jeruk", 5000));
//
//        List<Optional<MenuItem>> listActualMenuDrink = menuItemService.initiateMenuDrinks();
//        for (int i = 0; i<listActualMenuDrink.size();i++){
//            assertEquals(listExpectedMenuDrink.get(i).getIdMenu(), listActualMenuDrink.get(i).get().getIdMenu());
//            assertEquals(listExpectedMenuDrink.get(i).getMenuName(), listActualMenuDrink.get(i).get().getMenuName());
//            assertEquals(listExpectedMenuDrink.get(i).getMenuPrice(), listActualMenuDrink.get(i).get().getMenuPrice());
//        }
//    }
//
//    @Test
//    @DisplayName("Postive Test -> Cek List Initiate Menu")
//    void getMenuItemsTest(){
//        MenuItem tempMenu = new MenuItem();
//        List<MenuItem> listExpectedMenu = new ArrayList<>();
//        tempMenu.setIdMenu(1);
//        tempMenu.setMenuName("Nasi Goreng");
//        tempMenu.setMenuPrice(15000);
//        listExpectedMenu.add(tempMenu);
//        listExpectedMenu.add(new MenuItem(2,"Mie Goreng", 13000));
//        listExpectedMenu.add(new MenuItem(3,"Nasi + Ayam", 18000));
//        listExpectedMenu.add(new MenuItem(4,"Teh Manis", 3000));
//        listExpectedMenu.add(new MenuItem(5,"Jeruk", 5000));
//
//        System.out.println(listExpectedMenu.size());
//        menuItemService.initiateMenu();
//        List<Optional<MenuItem>> listActualMenu = menuItemService.getMenuItems();
//        System.out.println(listActualMenu.size());
//        for (int i=0; i<listActualMenu.size();i++){
//            assertEquals(listExpectedMenu.get(i).getIdMenu(), listActualMenu.get(i).get().getIdMenu());
//            assertEquals(listExpectedMenu.get(i).getMenuName(), listActualMenu.get(i).get().getMenuName());
//            assertEquals(listExpectedMenu.get(i).getMenuPrice(), listActualMenu.get(i).get().getMenuPrice());
//        }
//
//    }
    @Test
    @DisplayName("Positive Test -> Get Menu by Id")
     void getMenuIdTestPositive(){
        Integer id = menuItemService.getMenuId("Nasi Goreng");
        assertEquals(1, id);
    }

    @Test
    @DisplayName("Negative Test -> Get Menu by Id")
    void getMenuIdTestNegative(){
        Integer id = menuItemService.getMenuId("Nasi Goreng ");
        assertNotEquals(1, id);
    }

    @Test
    @DisplayName("Positive Test -> Get Price by Id")
    void getMenuPriceTestPositive(){
        Integer price = menuItemService.getMenuPrice("Nasi Goreng");
        assertEquals(15000, price);
    }

    @Test
    @DisplayName("Negative Test -> Get Menu by Id")
    void getMenuPriceTestNegative(){
        Integer price = menuItemService.getMenuPrice("Nasi Goreng ");
        assertNotEquals(15000, price);
    }



}


