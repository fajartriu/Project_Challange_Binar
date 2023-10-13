package org.example.view;
import org.example.utils.Constant;

public class OrderView {
    public void screenOrder(String menu, int harga){
        Constant.printHeaderMenu();
        System.out.println(
                Constant.handleMenuName(menu)+" | "+harga +"\n" +
                        "(input 0 untuk kembali)");
        // input menu yang di inginkan
        System.out.print("Qty => ");
    }
}