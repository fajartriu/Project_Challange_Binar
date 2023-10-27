package org.chalenge3.Challenge_3BinarFood.controller;

import org.chalenge3.Challenge_3BinarFood.model.Merchant;
import org.chalenge3.Challenge_3BinarFood.service.MerchantService;
import org.chalenge3.Challenge_3BinarFood.view.HomeMenu;
import org.chalenge3.Challenge_3BinarFood.view.MerchantView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    public void index(){
        List<Merchant> merchantList = merchantService.getAll();
        MerchantView.showAllMerchant(merchantList);
        MerchantView.showMerchantMenuOption();
        selectMerchantMenu();
    }

    private void merchantMenuOption(){

    }

    private void selectMerchantMenu(){
        Scanner scan = new Scanner(System.in);
        int merchantMenuSelected = scan.nextInt();
        if(merchantMenuSelected==1){
            addMerchant();
        }
    }

    private void addMerchant(){
        Merchant merchant = new Merchant();
        Scanner scan = new Scanner(System.in);
        System.out.println("Name: ");
        String name = scan.nextLine();
        merchant.setMerchantName(name);

        System.out.println("Location: ");
        String location = scan.nextLine();
        merchant.setMerchantLocation(location);

        merchantService.create(merchant);
    }
}
