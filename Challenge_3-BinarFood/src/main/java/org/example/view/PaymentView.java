package org.example.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.model.*;
import org.example.service.MenuItemService;
import org.example.service.NotesService;
import org.example.service.PaymentService;
import org.example.utils.Constant;

public class PaymentView {
    public void screenConfirmPayment(){
        PaymentService ps = new PaymentService();
        System.out.println(Constant.PRINTCONFIRMPAYMENT);
        ps.orderMenuAsc();
        System.out.print("\n");
        System.out.println(Constant.PRINTINPUTPAYMENT);
        System.out.print("=> ");
    }

    public void screenPembayaran(){
        PaymentService ps = new PaymentService();
        System.out.println(Constant.PRINTHEADERSTRUCT);
        ps.orderMenuAsc();
        System.out.println(Constant.PRINTFOOTERSTRUCT);

    }

}