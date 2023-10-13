package org.example.controller;

import java.util.Scanner;
import org.example.model.Data;
import org.example.model.OrderItem;
import org.example.service.AppService;
import org.example.service.OrderItemService;
import org.example.utils.Constant;
import org.example.view.ErrorView;
import org.example.view.HomeView;
import org.example.view.NotesView;
import org.example.view.OrderView;

public class OrderItemController {
    OrderItemService ois = new OrderItemService();
    public void home(){
        menu();
    }

    public void menu(){
        HomeView hv = new HomeView();
        hv.screenHome();
        selectMenu();
    }

    public void selectMenu(){
        try {
            int input = inputUser();
            switch (input) {
                case 1, 2, 3, 4, 5 -> orderMenu(input);
                case 0 -> {
                    System.out.println("Keluar aplikasi");
                    AppService.setExit(true);
                }
                case 99 -> {
                    System.out.println("Print data");
                    AppService.setExit(checkOutOrder());
                }
                default -> System.out.println("Menu yang di pesan belum tersedia, silahkan pesan menu yang lain");
            }
        }catch (Exception e){
            AppService.setExit(errorHandler());
        }

    }

    public void orderMenu(int number){
        OrderView ov = new OrderView();
        Integer idMenu = ois.orderMenu(number).get(0).getIdMenu();
        String menuName = ois.orderMenu(number).get(0).getMenuName();
        Integer menuPrice = ois.orderMenu(number).get(0).getMenuPrice();
        ov.screenOrder(menuName, menuPrice);
        try {
            int qty = inputUser();
            if (qty > 0){
                Data.orderItems.add(new OrderItem(idMenu, menuName, menuPrice, qty));
                selectNotes(number, menuName);
            } else {
                Constant.printBackToHome(menuName);
            }
        }catch (Exception e){
            AppService.setExit(errorHandler());
        }
    }

    public void selectNotes(int number, String menuName){
        NotesView nv = new NotesView();
        if(number == 1 || number == 2 || number == 3){
            try{
                nv.screenNotesMakanan();
                int no = inputUser();
                ois.chooseMakananNotes(no, menuName);
            }catch (Exception e){
                AppService.setExit(errorHandler());
            }

        }else if (number == 4 || number == 5){
            try{
                nv.screenNotesMinuman();
                int no = inputUser();
                ois.chooseMinumanNotes(no, menuName);
            }catch (Exception e){
                AppService.setExit(errorHandler());
            }

        }
    }



    public boolean checkOutOrder(){
        return ois.checkOutOrder();
    }

    public int inputUser(){
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        return scan.nextInt();
    }

    public String inputUserString(){
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        return scan.next();
    }

    public boolean errorHandler(){
        ErrorView ev = new ErrorView();
        ev.screenErrorInput();
        String inp = inputUserString().toLowerCase();
        if (inp.equals("y")){
            return false;
        }else if (inp.equals("n")){
            return true;
        }else {
            System.out.println("Masukkan perintah dengan benar!");
            errorHandler();
        }
        return false;
    }

}

