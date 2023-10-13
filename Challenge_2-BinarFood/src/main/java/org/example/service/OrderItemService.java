package org.example.service;

import org.example.controller.OrderItemController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.example.model.*;
import org.example.utils.Constant;
import org.example.view.PaymentView;

public class OrderItemService {


    List<MenuItem> tempOrder = new ArrayList<>();

    public List<MenuItem> orderMenu(int menuNumber) {
        MenuItemService mis = new MenuItemService();
        int i = (menuNumber-1);
        if (menuNumber == mis.getMenuItems().get(i).getIdMenu()){
            String menuName = mis.getMenuItems().get(i).getMenuName();
            Integer menuPrice = mis.getMenuItems().get(i).getMenuPrice();
            tempOrder.add(new MenuItem(mis.getMenuItems().get(i).getIdMenu(), menuName, menuPrice));
            return tempOrder;
        }
        return tempOrder;
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

    public void chooseMakananNotes(int no, String menuName){
        NotesService ns = new NotesService();
        int i = (no-1);
        if (ns.getNotesMakanan().get(i).getIdNotes() == no){
            Data.notes.add(new OrderNotes(ns.getNotesMakanan().get(i).getIdNotes(), ns.getNotesMakanan().get(i).getNotes(), menuName));
        }
    }

    public void chooseMinumanNotes(int no, String menuName){
        NotesService ns = new NotesService();
        int i = (no-1);
        if (ns.getNotesMinuman().get(i).getIdNotes() == no){
            Data.notes.add(new OrderNotes(ns.getNotesMinuman().get(i).getIdNotes(), ns.getNotesMinuman().get(i).getNotes(), menuName));
        }
    }

    public void printStruck() {
        PaymentView pv = new PaymentView();
        FileWriter myWriter = null;
        File currentDir = new File("struk.txt");
        String path = currentDir.getAbsolutePath();
        try(InputStream input = Files.newInputStream(Path.of(path))){
            myWriter = new FileWriter(path);
            myWriter.write(Constant.PRINTHEADERSTRUCT);
            int sumQty = 0;
            int sumTotal = 0;
            for (OrderSubTotal order : pv.tempSubTotal()) {
                if (order.getQuantity() > 9 && order.getMenuPrice() > 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "    " + order.getMenuPrice() + "  " + order.getSubTotal() + "\n");
                } else if (order.getQuantity() < 9 && order.getMenuPrice() > 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "     " + order.getMenuPrice() + "  " + order.getSubTotal() + "\n");
                } else if (order.getQuantity() < 9 && order.getMenuPrice() < 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "     " + order.getMenuPrice() + "   " + order.getSubTotal() + "\n");
                } else if (order.getQuantity() > 9 && order.getMenuPrice() < 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "    " + order.getMenuPrice() + "   " + order.getSubTotal() + "\n");
                }
                sumQty += order.getQuantity();
                sumTotal += order.getSubTotal();
            }

            if (sumQty > 9) {
                myWriter.write(Constant.sumTotalQtyMore(sumQty, sumTotal));
            } else {
                myWriter.write(Constant.sumTotalQtyLess(sumQty, sumTotal));
            }

            myWriter.write("\nNotes : \n");
            for (TotalOrderNotes totalOrderNotes:pv.handleRedundantNotes()){
                myWriter.write(totalOrderNotes.getTotalNotes()+" "+totalOrderNotes.getName()+" "+totalOrderNotes.getNotes());
            }

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
