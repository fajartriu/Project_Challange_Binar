import org.example.model.Data;
import org.example.model.MenuItem;
import org.example.model.Notes;
import org.example.model.OrderItem;
import org.example.service.OrderItemService;
import org.example.service.PaymentService;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class OrderMenuTest {
    MenuItemTest menuItemTest;
    OrderItemService orderItemService;

    @BeforeAll
    static void initiateMenu(){
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

        MenuItem tempMenuDrink5 = new MenuItem(5,"Jeruk", 5000);
        Data.menuItems.add(Optional.of(tempMenuDrink5));

        Notes notes = new Notes();
        notes.setIdNotes(1);
        notes.setNotes("Pedas");
        Data.notesMakanan.add(Optional.of(notes));
        Data.notesMakanan.add(Optional.of(new Notes(2, "Sedang")));
        Data.notesMakanan.add(Optional.of(new Notes(3, "Biasa")));

        Data.notesMinuman.add(Optional.of(new Notes(1, "Panas")));
        Data.notesMinuman.add(Optional.of(new Notes(2, "Dingin")));
    }

    @Test
    @DisplayName("Positive Test -> Cek data Order Item")
    void chooseMakananNotesTestPositive(){
        orderItemService = new OrderItemService();
        OrderItem expect = new OrderItem(1, "Nasi Goreng", 15000, 1, 1,"Pedas");
        List<OrderItem> expectedList = new ArrayList<>();
        expectedList.add(expect);

        List<Optional<OrderItem>> actualList = orderItemService.chooseMakananNotess(1,1,"Nasi Goreng", 15000, 1);

        if (expectedList.size() == actualList.size()){
            for (int i=0;i<actualList.size();i++){
                Assertions.assertEquals(expectedList.get(i).getIdMenu(), actualList.get(i).get().getIdMenu());
                Assertions.assertEquals(expectedList.get(i).getMenuName(), actualList.get(i).get().getMenuName());
                Assertions.assertEquals(expectedList.get(i).getMenuPrice(), actualList.get(i).get().getMenuPrice());
                Assertions.assertEquals(expectedList.get(i).getQuantity(), actualList.get(i).get().getQuantity());
                Assertions.assertEquals(expectedList.get(i).getIdNotes(), actualList.get(i).get().getIdNotes());
                Assertions.assertEquals(expectedList.get(i).getNotes(), actualList.get(i).get().getNotes());
            }
        }
    }

    @Test
    @DisplayName("Negative Test -> Cek data Order Item")
    void chooseMakananNotesTestNegative(){
        orderItemService = new OrderItemService();
        OrderItem expect = new OrderItem();
        expect.setIdMenu(1);
        expect.setMenuName("Nasi Goreng");
        expect.setMenuPrice(1500);
        expect.setQuantity(1);
        expect.setIdNotes(1);
        expect.setNotes("Pedas");
        List<OrderItem> expectedList = new ArrayList<>();
        expectedList.add(expect);

        List<Optional<OrderItem>> actualList = orderItemService.chooseMakananNotess(2,2,"Mie Goreng", 13000, 2);

        if (expectedList.size() == actualList.size()){
            for (int i=0;i<actualList.size();i++){
                Assertions.assertNotEquals(expectedList.get(i).getIdMenu(), actualList.get(i).get().getIdMenu());
                Assertions.assertNotEquals(expectedList.get(i).getMenuName(), actualList.get(i).get().getMenuName());
                Assertions.assertNotEquals(expectedList.get(i).getMenuPrice(), actualList.get(i).get().getMenuPrice());
                Assertions.assertNotEquals(expectedList.get(i).getQuantity(), actualList.get(i).get().getQuantity());
                Assertions.assertNotEquals(expectedList.get(i).getIdNotes(), actualList.get(i).get().getIdNotes());
                Assertions.assertNotEquals(expectedList.get(i).getNotes(), actualList.get(i).get().getNotes());
            }
        }
    }

    @Test
    @DisplayName("Positive  -> Cek data Order Item")
    void chooseMinumanNotesTestPositive(){
        orderItemService = new OrderItemService();
        OrderItem expect = new OrderItem(4, "Teh Manis", 3000, 1, 1,"Panas");
        List<OrderItem> expectedList = new ArrayList<>();
        expectedList.add(expect);

        List<Optional<OrderItem>> actualList = orderItemService.chooseMinumanNotess(4,1,"Teh Manis", 3000, 1);

        if (expectedList.size() == actualList.size()){
            for (int i=0;i<actualList.size();i++){
                Assertions.assertEquals(expectedList.get(i).getIdMenu(), actualList.get(i).get().getIdMenu());
                Assertions.assertEquals(expectedList.get(i).getMenuName(), actualList.get(i).get().getMenuName());
                Assertions.assertEquals(expectedList.get(i).getMenuPrice(), actualList.get(i).get().getMenuPrice());
                Assertions.assertEquals(expectedList.get(i).getQuantity(), actualList.get(i).get().getQuantity());
                Assertions.assertEquals(expectedList.get(i).getIdNotes(), actualList.get(i).get().getIdNotes());
                Assertions.assertEquals(expectedList.get(i).getNotes(), actualList.get(i).get().getNotes());
            }
        }
    }

    @Test
    @DisplayName("Negative Test -> Cek data Order Item")
    void chooseMinumanNotesTestNegative(){
        orderItemService = new OrderItemService();
        OrderItem expect = new OrderItem(4, "Teh Manis", 3000, 1, 1,"Panas");
        List<OrderItem> expectedList = new ArrayList<>();
        expectedList.add(expect);

        List<Optional<OrderItem>> actualList = orderItemService.chooseMinumanNotess(5,2,"Jeruk", 5000, 2);

        if (expectedList.size() == actualList.size()){
            for (int i=0;i<actualList.size();i++){
                Assertions.assertNotEquals(expectedList.get(i).getIdMenu(), actualList.get(i).get().getIdMenu());
                Assertions.assertNotEquals(expectedList.get(i).getMenuName(), actualList.get(i).get().getMenuName());
                Assertions.assertNotEquals(expectedList.get(i).getMenuPrice(), actualList.get(i).get().getMenuPrice());
                Assertions.assertNotEquals(expectedList.get(i).getQuantity(), actualList.get(i).get().getQuantity());
                Assertions.assertNotEquals(expectedList.get(i).getIdNotes(), actualList.get(i).get().getIdNotes());
                Assertions.assertNotEquals(expectedList.get(i).getNotes(), actualList.get(i).get().getNotes());
            }
        }
    }

    @Test
    @DisplayName("Positive Test -> get IDNotes by Name")
    void getIDNotesByNameTestPositive(){
        PaymentService paymentService = new PaymentService();
        OrderItem oi = new OrderItem(1, "Nasi Goreng", 15000, 1, 1, "Pedas");
        Data.orderItems.add(Optional.of(oi));

        Integer actual = paymentService.getIdNotesByName("Pedas");
        Assertions.assertEquals(1,actual);
    }

    @Test
    @DisplayName("Negative Test -> get IDNotes by Name")
    void getIDNotesByNameTestNegative(){
        PaymentService paymentService = new PaymentService();
        OrderItem oi = new OrderItem(1, "Nasi Goreng", 15000, 1, 1, "Pedas");
        Data.orderItems.add(Optional.of(oi));

        Integer actual = paymentService.getIdNotesByName("Sedang");
        Assertions.assertNotEquals(2,actual);
    }

}
