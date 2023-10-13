package org.example.service;

import org.example.model.Data;
import org.example.model.OrderItem;
import org.example.model.OrderSubTotal;
import org.example.utils.Constant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaymentService {

    public Integer getIdNotesByName(String name){
        return Data.orderItems.stream().filter(Optional::isPresent).filter(orderItem -> orderItem.get().getNotes().equals(name)).findFirst().map(orderItem -> orderItem.get().getIdNotes()).orElse(null);
    }
    public List<OrderSubTotal> tempSubTotal(){
        MenuItemService mis = new MenuItemService();
        Map<String, Integer> groupQty = Data.orderItems.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.groupingBy(OrderItem::getConcatMenuNameNotes, Collectors.summingInt(OrderItem::getQuantity)));

        List<OrderSubTotal> listOrder = new ArrayList<>();
        int sub = 0;
        for (Map.Entry<String,Integer> entry : groupQty.entrySet()){
            Integer menuId = mis.getMenuId(entry.getKey().substring(0, entry.getKey().lastIndexOf(" ")));
            Integer menuPrice = mis.getMenuPrice(entry.getKey().substring(0, entry.getKey().lastIndexOf(" ")));
            String notes = entry.getKey().substring(entry.getKey().lastIndexOf(" ")+1);
            Integer idNotes = getIdNotesByName(entry.getKey().substring(entry.getKey().lastIndexOf(" ")+1));
            sub = entry.getValue() * menuPrice;
            listOrder.add(new OrderSubTotal(menuId, entry.getKey(), menuPrice, entry.getValue(), idNotes ,notes, sub));
        }
        return listOrder;
    }

    public void orderMenuAsc(){
        PaymentService ps = new PaymentService();
        List<OrderSubTotal> orderMenu = ps.tempSubTotal().stream()
                .sorted(MenuComparator::compareByName)
                .toList();

        AtomicInteger sumQty = new AtomicInteger();
        AtomicInteger sumTotal = new AtomicInteger();
        orderMenu.forEach(orderSubTotal -> {
            if (orderSubTotal.getQuantity() > 9 && orderSubTotal.getMenuPrice() > 10000) {
                System.out.println(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "    " + orderSubTotal.getMenuPrice() + "  " +orderSubTotal.getSubTotal());
            } else if (orderSubTotal.getQuantity() < 9 && orderSubTotal.getMenuPrice() > 10000) {
                System.out.println(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "     " + orderSubTotal.getMenuPrice() + "  " +orderSubTotal.getSubTotal());
            } else if (orderSubTotal.getQuantity() < 9 && orderSubTotal.getMenuPrice() < 10000){
                System.out.println(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "     " + orderSubTotal.getMenuPrice() + "   "+orderSubTotal.getSubTotal());
            } else if (orderSubTotal.getQuantity() > 9 && orderSubTotal.getMenuPrice() < 10000) {
                System.out.println(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "    " + orderSubTotal.getMenuPrice() + "   "+orderSubTotal.getSubTotal());
            }
            sumQty.addAndGet(orderSubTotal.getQuantity());
            sumTotal.addAndGet(orderSubTotal.getSubTotal());
        });
        if (sumQty.intValue()>9){
            System.out.println(Constant.sumTotalQtyMore(sumQty.intValue(),sumTotal.intValue()));
        }else{
            System.out.println(Constant.sumTotalQtyLess(sumQty.intValue(),sumTotal.intValue()));
        }
    }

    public void orderMenuAscPrintStruct(FileWriter myWriter) throws IOException {
        PaymentService ps = new PaymentService();
        List<OrderSubTotal> orderMenu = ps.tempSubTotal().stream()
                .sorted(MenuComparator::compareByName)
                .toList();

        AtomicInteger sumQty = new AtomicInteger();
        AtomicInteger sumTotal = new AtomicInteger();
        orderMenu.forEach(orderSubTotal -> {
            if (orderSubTotal.getQuantity() > 9 && orderSubTotal.getMenuPrice() > 10000) {
                try {
                    myWriter.write(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "    " + orderSubTotal.getMenuPrice() + "  " +orderSubTotal.getSubTotal()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (orderSubTotal.getQuantity() < 9 && orderSubTotal.getMenuPrice() > 10000) {
                try {
                    myWriter.write(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "     " + orderSubTotal.getMenuPrice() + "  " +orderSubTotal.getSubTotal()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (orderSubTotal.getQuantity() < 9 && orderSubTotal.getMenuPrice() < 10000){
                try {
                    myWriter.write(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "     " + orderSubTotal.getMenuPrice() + "   "+orderSubTotal.getSubTotal()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (orderSubTotal.getQuantity() > 9 && orderSubTotal.getMenuPrice() < 10000) {
                try {
                    myWriter.write(Constant.handleMenuName(orderSubTotal.getMenuName()) + orderSubTotal.getQuantity() + "    " + orderSubTotal.getMenuPrice() + "   "+orderSubTotal.getSubTotal()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            sumQty.addAndGet(orderSubTotal.getQuantity());
            sumTotal.addAndGet(orderSubTotal.getSubTotal());
        });
        if (sumQty.intValue()>9){
            myWriter.write(Constant.sumTotalQtyMore(sumQty.intValue(),sumTotal.intValue()));
        }else{
            myWriter.write(Constant.sumTotalQtyLess(sumQty.intValue(),sumTotal.intValue()));
        }
    }
}

class MenuComparator{
    public static int compareByName(OrderSubTotal o1, OrderSubTotal o2){
        return o1.getMenuName().compareTo(o2.getMenuName());
    }
}
