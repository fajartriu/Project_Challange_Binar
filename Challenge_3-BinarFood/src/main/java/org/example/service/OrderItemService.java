package org.example.service;

import org.example.controller.OrderItemController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.example.model.*;
import org.example.utils.Constant;
import org.example.view.NotesView;
import org.example.view.OrderView;
import org.example.view.PaymentView;

import java.io.FileWriter;

public class OrderItemService {

    public void orderMenu(int menuNumber){
        MenuItemService mis = new MenuItemService();
        OrderView ov = new OrderView();
        OrderItemController oic = new OrderItemController();
        int i = (menuNumber-1);
        if(mis.getMenuItems().get(i).isPresent()){
            if (menuNumber == mis.getMenuItems().get(i).get().getIdMenu()){
                String menuName = mis.getMenuItems().get(i).get().getMenuName();
                Integer menuPrice = mis.getMenuItems().get(i).get().getMenuPrice();
                ov.screenOrder(menuName, menuPrice);
                try {
                    int qty = oic.inputUser();
                    if (qty > 0){
                        selectNotes(mis.getMenuItems().get(i).get().getIdMenu(), menuName, menuPrice, qty);
                    } else {
                        Constant.printBackToHome(menuName);
                    }
                }catch (Exception e){
                    AppService.setExit(oic.errorHandler());
                }
            }
        }

    }

    public void selectNotes(Integer idMenu, String menuName, Integer menuPrice, Integer qty){
        NotesView nv = new NotesView();
        OrderItemController oic = new OrderItemController();
        if(idMenu == 1 || idMenu == 2 || idMenu == 3){
            try{
                nv.screenNotesMakanan();
                int no = oic.inputUser();
                chooseMakananNotes(idMenu, no, menuName, menuPrice, qty);
            }catch (Exception e){
                AppService.setExit(oic.errorHandler());
            }

        }else if (idMenu == 4 || idMenu == 5){
            try{
                nv.screenNotesMinuman();
                int no = oic.inputUser();
                chooseMinumanNotes(idMenu, no, menuName, menuPrice, qty);
            }catch (Exception e){
                AppService.setExit(oic.errorHandler());
            }

        }
    }

    public void chooseMakananNotes(Integer idMenu, Integer idNotes, String menuName, Integer menuPrice, Integer qty){
        NotesService ns = new NotesService();
        int i = (idNotes-1);
        if (ns.getNotesMakanan().get(i).isPresent()){
            if (Objects.equals(ns.getNotesMakanan().get(i).get().getIdNotes(), idNotes)){
                Data.orderItems.add(Optional.of(new OrderItem(idMenu, menuName, menuPrice, qty, ns.getNotesMakanan().get(i).get().getIdNotes(), ns.getNotesMakanan().get(i).get().getNotes())));
            }
        }
    }

    public List<Optional<OrderItem>> chooseMakananNotess(Integer idMenu, Integer idNotes, String menuName, Integer menuPrice, Integer qty){
        NotesService ns = new NotesService();

        int i = (idNotes-1);
        if (ns.getNotesMakanan().get(i).isPresent()){
            if (Objects.equals(ns.getNotesMakanan().get(i).get().getIdNotes(), idNotes)){
                Data.orderItems.add(Optional.of(new OrderItem(idMenu, menuName, menuPrice, qty, ns.getNotesMakanan().get(i).get().getIdNotes(), ns.getNotesMakanan().get(i).get().getNotes())));
                return Data.orderItems;
            }
        }
        return null;
    }

    public void chooseMinumanNotes(Integer idMenu, Integer idNotes, String menuName, Integer menuPrice, Integer qty){
        NotesService ns = new NotesService();
        int i = (idNotes-1);
        if (ns.getNotesMinuman().get(i).isPresent()){
            if (Objects.equals(ns.getNotesMinuman().get(i).get().getIdNotes(), idNotes)){
                Data.orderItems.add(Optional.of(new OrderItem(idMenu, menuName, menuPrice, qty, ns.getNotesMinuman().get(i).get().getIdNotes(), ns.getNotesMinuman().get(i).get().getNotes())));
            }
        }
    }

    public List<Optional<OrderItem>> chooseMinumanNotess(Integer idMenu, Integer idNotes, String menuName, Integer menuPrice, Integer qty){
        NotesService ns = new NotesService();
        int i = (idNotes-1);
        if (ns.getNotesMinuman().get(i).isPresent()){
            if (Objects.equals(ns.getNotesMinuman().get(i).get().getIdNotes(), idNotes)){
                Data.orderItems.add(Optional.of(new OrderItem(idMenu, menuName, menuPrice, qty, ns.getNotesMinuman().get(i).get().getIdNotes(), ns.getNotesMinuman().get(i).get().getNotes())));
                return Data.orderItems;
            }
        }
        return null;
    }

    public boolean checkOutOrder(){
        PaymentView pv = new PaymentView();
        OrderItemController oic = new OrderItemController();
        try {
            pv.screenConfirmPayment();
            int choose = oic.inputUser();
            switch (choose) {
                case 1 -> {
                    pv.screenPembayaran();
                    printStruck();
                    Data.orderItems.clear();
                    return false;
                }
                case 2 -> {
                    System.out.println("Kembali ke menu utama");
                    return false;
                }
                case 0 -> {
                    System.out.println("Keluar Aplikasi");
                    return true;
                }
                default -> {
                    return oic.errorHandler();
                }
            }
        }catch (Exception e){
            return oic.errorHandler();
        }
    }



    public void printStruck() {
        PaymentService ps = new PaymentService();
        FileWriter myWriter = null;
        File currentDir = new File("struk.txt");
        String path = currentDir.getAbsolutePath();
        try(InputStream input = Files.newInputStream(Path.of(path))){
            myWriter = new FileWriter(path);
            myWriter.write(Constant.PRINTHEADERSTRUCT);
            ps.orderMenuAscPrintStruct(myWriter);

            myWriter.write(Constant.PRINTFOOTERSTRUCT);
            myWriter.close();
            System.out.println("Struk berhasil di cetak. Terimakasih sudah belanja di BinarFood\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            try {
                myWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
