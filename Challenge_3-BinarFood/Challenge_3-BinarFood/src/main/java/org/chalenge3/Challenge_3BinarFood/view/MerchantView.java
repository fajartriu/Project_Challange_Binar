package org.chalenge3.Challenge_3BinarFood.view;

import org.chalenge3.Challenge_3BinarFood.model.Merchant;

import java.util.List;

public class MerchantView {
    public static void showAllMerchant(List<Merchant> merchantList){
        System.out.println("Show all merchant");
    }

    public static void showMerchantMenuOption(){
        System.out.println("Silakan pilih mebu :");
        System.out.println("1. Tambah");
        System.out.println("2. Edit Status");
        System.out.println("3. Tampilkan yang sedang buka");
    }
}
