package org.example;

import org.example.menu.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // declare class Menu
        Menu menu = new Menu();
        // declare all menu
        HashMap<String, Integer> menus = new HashMap<String, Integer>();
        menus.put("Nasi Goreng", 15000);
        menus.put("Mie Goreng", 13000);
        menus.put("Nasi + Ayam", 18000);
        menus.put("Es Teh Manis", 3000);
        menus.put("Es Jeruk", 5000);
        //=============================================
        int stat = 1;
        int i = 0;
        ArrayList<String> menuName = new ArrayList();
        ArrayList<Integer> quantity = new ArrayList();
        ArrayList<Integer> subT = new ArrayList();
        while (stat == 1){
            int statBack = 1;
            // tampilin pembuka warteg dan menu
            menu.screenMenu();
            // input menu yang di inginkan
            Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Pilih menu:");
            int menuUser = inputUser.nextInt();  // Read user input
            System.out.println("Menu pilihan: " + menuUser);  // Output user input
            //=========================================
            if (menuUser == 0){
                stat = 0;
            }
            else{
                //declare confirm order
                if (menuUser == 1 && statBack == 1){
                    int qty = menu.screenConfirmPesanan("Nasi Goreng", menus.get("Nasi Goreng"));
                    if(qty == 0){
                        statBack = 0;
                    }
                    else{
                        int subTotal = menu.calculatePerOrder(qty, menus.get("Nasi Goreng"));
                        menuName.add("Nasi Goreng       ");
                        quantity.add(qty);
                        subT.add(subTotal);
                    }
                }
                else if(menuUser == 2 && statBack == 1){
                    int qty = menu.screenConfirmPesanan("Mie Goreng", menus.get("Mie Goreng"));
                    if(qty == 0){
                        statBack = 0;
                    }
                    else {
                        int subTotal = menu.calculatePerOrder(qty, menus.get("Mie Goreng"));
                        menuName.add("Mie Goreng        ");
                        quantity.add(qty);
                        subT.add(subTotal);
                    }
                }
                else if(menuUser == 3 && statBack == 1){
                    int qty = menu.screenConfirmPesanan("Nasi + Ayam", menus.get("Nasi + Ayam"));
                    if(qty == 0){
                        statBack = 0;
                    }
                    else {
                        int subTotal = menu.calculatePerOrder(qty, menus.get("Nasi + Ayam"));
                        menuName.add("Nasi + Ayam       ");
                        quantity.add(qty);
                        subT.add(subTotal);
                    }
                }
                else if(menuUser == 4 && statBack == 1){
                    int qty = menu.screenConfirmPesanan("Es Teh Manis", menus.get("Es Teh Manis"));
                    if(qty == 0){
                        statBack = 0;
                    }
                    else {
                        int subTotal = menu.calculatePerOrder(qty, menus.get("Es Teh Manis"));
                        menuName.add("Es Teh Manis      ");
                        quantity.add(qty);
                        subT.add(subTotal);
                    }
                }
                else if(menuUser == 5 && statBack == 1){
                    int qty = menu.screenConfirmPesanan("Es Jeruk", menus.get("Es Jeruk"));
                    if(qty == 0){
                        statBack = 0;
                    }
                    else {
                        int subTotal = menu.calculatePerOrder(qty, menus.get("Es Jeruk"));
                        menuName.add("Es Jeruk          ");
                        quantity.add(qty);
                        subT.add(subTotal);
                    }
                }
                else if(menuUser == 99 && statBack == 1){
                    int inp = menu.screenConfirmPembayaran(menuName, quantity, subT);
                    if (inp == 1){
                        menu.screenPembayaran(menuName, quantity, subT);
                        menu.printStruck(menuName, quantity, subT);
                        stat = 0;
                    }
                    else if(inp == 2){
                        statBack = 0;
                    } else if (inp == 0) {
                        stat = 0;
                    }
                }
            }
            i++;
        }
    }
}