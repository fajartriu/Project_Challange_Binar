package org.example.view;

import org.example.model.Data;
import org.example.utils.Constant;

public class HomeView {
    public void screenHome(){
        Constant.printHeaderMenu();
        System.out.println("Silahkan pilih makanan :");
        int i = 0;
        for(i = 0; i< Data.menuItems.size(); i++){
            handleSpaceMenu(i);
        }
        System.out.println("99. Pesan dan Bayar\n" +
                "0. Keluar aplikasi");
        // input menu yang di inginkan
        System.out.print("Pilih menu : ");
    }

    public void handleSpaceMenu(int i){
        if(i == 0 && Data.menuItems.get(0).isPresent()){
            System.out.println((1)+". "+Data.menuItems.get(0).get().getMenuName()+"  | "+Data.menuItems.get(0).get().getMenuPrice());
        } else if (i == 1 && Data.menuItems.get(1).isPresent()) {
            System.out.println((2)+". "+Data.menuItems.get(1).get().getMenuName()+"   | "+Data.menuItems.get(1).get().getMenuPrice());
        } else if (i == 2 && Data.menuItems.get(2).isPresent()) {
            System.out.println((3)+". "+Data.menuItems.get(2).get().getMenuName()+"  | "+Data.menuItems.get(2).get().getMenuPrice());
        } else if (i == 3 && Data.menuItems.get(3).isPresent()) {
            System.out.println((4)+". "+Data.menuItems.get(3).get().getMenuName()+"    | "+Data.menuItems.get(3).get().getMenuPrice());
        } else if (i == 4 && Data.menuItems.get(4).isPresent()) {
            System.out.println((5)+". "+Data.menuItems.get(4).get().getMenuName()+"        | "+Data.menuItems.get(4).get().getMenuPrice());
        }else{
            System.out.println("Belum tersedia");
        }
    }
}

