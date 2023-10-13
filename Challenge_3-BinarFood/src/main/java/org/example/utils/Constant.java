package org.example.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class Constant {

    private Constant() {
    }

    public static void printHeaderMenu(){
        String text = """
                ==========================
                Selamat datang di BinarFud
                ==========================
                """;
        System.out.println(text);
    }

    public static final String ERRORINPUTMESSAGE = """
           ==========================
           Mohon masukkan input
           pilihan anda
           ==========================
           (Y) untuk lanjut
           (n) untuk keluar
           """;

    public static final String PRINTCONFIRMPAYMENT = """
                =============================================
                          Konfirmasi & Pembayaran
                =============================================
                """;
    public static final String PRINTFOOTERSTRUCT = """
                
                Pembayaran : BinarCash
                                
                =====================================
                Simpan struk ini sebagai bukti 
                pembayaran
                =====================================
                """;


    public static final String PRINTHEADERSTRUCT = """
            =============================================
                              BinarFud
            =============================================
                
            Terima kasih sudah memesan di Binarfud
                
            Dibawah ini adalah pesanan anda
                
            """;

    public static final String PRINTINPUTPAYMENT = """
                1. Konfirmasi dan Bayar
                2. Kembali ke menu Utama
                0. Keluar aplikasi
                """;

    public static void printBackToHome(String makanan) {
        System.out.println("""
                =====================================
                Minimal 1 jumlah
                pesanan!
                =====================================
                """);
        System.out.println("Kembali ke menu utama dan belum melakukan pesanan "+makanan);
    }

    public static String handleMenuName(String menuName){
        String name = "";
        switch (menuName) {
            case "Nasi Goreng Sedang" -> {
                name = "Nasi Goreng Sedang      ";
                return name;
            }
            case "Nasi Goreng Pedas" -> {
                name = "Nasi Goreng Pedas       ";
                return name;
            }
            case "Nasi Goreng Biasa" -> {
                name = "Nasi Goreng Biasa       ";
                return name;
            }
            case "Mie Goreng Sedang" -> {
                name = "Mie Goreng Sedang       ";
                return name;
            }
            case "Mie Goreng Pedas" -> {
                name = "Mie Goreng Pedas        ";
                return name;
            }
            case "Mie Goreng Biasa" -> {
                name = "Mie Goreng Biasa        ";
                return name;
            }
            case "Nasi + Ayam Pedas" -> {
                name = "Nasi + Ayam Pedas       ";
                return name;
            }
            case "Nasi + Ayam Sedang" -> {
                name = "Nasi + Ayam Sedang      ";
                return name;
            }
            case "Nasi + Ayam Biasa" -> {
                name = "Nasi + Ayam Biasa       ";
                return name;
            }
            case "Teh Manis Dingin" -> {
                name = "Teh Manis Dingin        ";
                return name;
            }
            case "Teh Manis Panas" -> {
                name = "Teh Manis Panas         ";
                return name;
            }
            case "Jeruk Dingin" -> {
                name = "Jeruk Dingin            ";
                return name;
            }
            case "Jeruk Panas" -> {
                name = "Jeruk Panas             ";
                return name;
            }
            default -> {
                return name;
            }
        }
    }

    public static String handleMenuNameOrder(String menuName){
        String name = "";
        switch (menuName) {
            case "Nasi Goreng" -> {
                name = "Nasi Goreng       ";
                return name;
            }
            case "Mie Goreng" -> {
                name = "Mie Goreng        ";
                return name;
            }
            case "Nasi + Ayam" -> {
                name = "Nasi + Ayam       ";
                return name;
            }
            case "Teh Manis" -> {
                name = "Teh Manis        ";
                return name;
            }
            case "Jeruk" -> {
                name = "Jeruk            ";
                return name;
            }
            default -> {
                return name;
            }
        }
    }

    public static String sumTotalQtyMore(int sumQty, int sumTotal){
        return "\n--------------------------------------------+\n" +
                "Total                   "+sumQty+"           "+sumTotal+"\n";
    }

    public static String sumTotalQtyLess(int sumQty, int sumTotal){
        return "\n--------------------------------------------+\n" +
                "Total                   "+sumQty+"            "+sumTotal+"\n";
    }

    public static final String VARIANMAKANAN = """
            ==========================
            Pilih varian makanan
            ==========================
            1. Pedas
            2. Sedang
            3. Biasa
            """;

    public static final String VARIANMINUMAN = """
            ==========================
            Pilih varian minuman
            ==========================
            1. Panas
            2. Dingin
            """;



}