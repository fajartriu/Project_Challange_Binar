package org.example.menu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public void screenMenu(){
        System.out.println("==========================\n" +
                "Selamat datang di BinarFud\n" +
                "==========================\n" +
                "\n" +
                "Silahkan pilih makanan :\n" +
                "1. Nasi Goreng  | 15.000\n" +
                "2. Mie Goreng   | 13.000\n" +
                "3. Nasi + Ayam  | 18.000\n" +
                "4. Es Teh Manis | 3.000\n" +
                "5. Es Jeruk     | 5.000\n" +
                "99. Pesan dan Bayar\n" +
                "0. Keluar aplikasi");
    }

//    public int qtyUser(){
//        // input menu yang di inginkan
//        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Qty => ");
//        int qty = inputUser.nextInt();  // Read user input
//        return qty;
//    }

    public int screenConfirmPesanan(String mapMenu, int mapHarga){
        System.out.println("==========================\n" +
                "Selamat datang di BinarFud\n" +
                "==========================\n" +
                "\n" +
                mapMenu+"    | "+mapHarga +"\n" +
                "(input 0 untuk kembali)");
        // input menu yang di inginkan
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Qty => ");
        int qty = inputUser.nextInt();  // Read user input
        return qty;
    }

    public int calculatePerOrder(int qty, int mapHarga){
        int subTotal = qty*mapHarga;
        return subTotal;
    }

    public int screenConfirmPembayaran(ArrayList<String> menuName, ArrayList<Integer> quantity, ArrayList<Integer> subTotal){
        System.out.println("==========================\n" +
                "Konfirmasi & Pembayaran\n" +
                "==========================\n");
        if (menuName.size() == quantity.size() && menuName.size() == subTotal.size()){
            int sumQty = 0;
            int sumTotal = 0;
            for (int i = 0; i < quantity.size(); i++){
                if(quantity.get(i)>9) {
                    System.out.println(menuName.get(i) + quantity.get(i)+"      "+subTotal.get(i));
                }else{
                    System.out.println(menuName.get(i) + quantity.get(i)+"       "+subTotal.get(i));
                }
                sumQty += quantity.get(i);
                sumTotal += subTotal.get(i);
            }
            if (sumQty>9){
                System.out.println("-------------------------------+\n" +
                        "Total             "+sumQty+"      "+sumTotal+"\n");
            }else{
                System.out.println("-------------------------------+\n" +
                        "Total             "+sumQty+"       "+sumTotal+"\n");
            }

        }
        // input menu yang di inginkan
        Scanner inputUser = new Scanner(System.in);  // Create a Scanner object
        System.out.println("1. Konfirmasi dan Bayar\n" +
                "2. Kembali ke menu Utama\n" +
                "0. Keluar aplikasi\n\n" +
                "=> ");
        int inpUser = inputUser.nextInt();  // Read user input
        return inpUser;
    }

    public void screenPembayaran(ArrayList<String> menuName, ArrayList<Integer> quantity, ArrayList<Integer> subTotal){
        System.out.println("==========================\n" +
                "BinarFud\n" +
                "==========================\n\n" +
                "Terima kasih sudah memesan\n" +
                "di Binarfud\n" +
                "\nDibawah ini adalah pesanan anda\n");
        if (menuName.size() == quantity.size() && menuName.size() == subTotal.size()){
            int sumQty = 0;
            int sumTotal = 0;
            for (int i = 0; i < quantity.size(); i++){
                if(quantity.get(i)>9) {
                    System.out.println(menuName.get(i) + quantity.get(i)+"      "+subTotal.get(i));
                }else{
                    System.out.println(menuName.get(i) + quantity.get(i)+"       "+subTotal.get(i));
                }
                sumQty += quantity.get(i);
                sumTotal += subTotal.get(i);
            }
            if (sumQty>9){
                System.out.println("-------------------------------+\n" +
                        "Total             "+sumQty+"      "+sumTotal+"\n");
            }else{
                System.out.println("-------------------------------+\n" +
                        "Total             "+sumQty+"       "+sumTotal+"\n");
            }
        }
        System.out.println("\nPembayaran : BinarCash\n\n" +
                "==========================\n" +
                "Simpan struk ini sebagai\n" +
                "bukti pembayaran\n" +
                "==========================");
    }

    public void printStruck(ArrayList<String> menuName, ArrayList<Integer> quantity, ArrayList<Integer> subTotal){
        try {
            FileWriter myWriter = new FileWriter("struk.txt");
            myWriter.write("==========================\n" +
                    "BinarFud\n" +
                    "==========================\n\n" +
                    "Terima kasih sudah memesan\n" +
                    "di Binarfud\n" +
                    "\nDibawah ini adalah pesanan anda\n");
            if (menuName.size() == quantity.size() && menuName.size() == subTotal.size()){
                int sumQty = 0;
                int sumTotal = 0;
                for (int i = 0; i < quantity.size(); i++){
                    if(quantity.get(i)>9) {
                        myWriter.write(menuName.get(i) + quantity.get(i)+"      "+subTotal.get(i)+"\n");
                    }else{
                        myWriter.write(menuName.get(i) + quantity.get(i)+"       "+subTotal.get(i)+"\n");
                    }
                    sumQty += quantity.get(i);
                    sumTotal += subTotal.get(i);
                }
                if (sumQty>9){
                    myWriter.write("-------------------------------+\n" +
                            "Total             "+sumQty+"      "+sumTotal+"\n");
                }else{
                    myWriter.write("-------------------------------+\n" +
                            "Total             "+sumQty+"       "+sumTotal+"\n");
                }
            }
            myWriter.write("\nPembayaran : BinarCash\n\n" +
                    "==========================\n" +
                    "Simpan struk ini sebagai\n" +
                    "bukti pembayaran\n" +
                    "==========================");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

