package org.chalenge3.Challenge_3BinarFood.controller;

import org.chalenge3.Challenge_3BinarFood.view.HomeMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class HomeController {
    @Autowired
    MerchantController merchantController;

    private final static Logger logger = LoggerFactory.getLogger(MerchantController.class);
    public void home(){
        HomeMenu.welcomeMessage();
        HomeMenu.mainMenuOption();
        selectMainMenu();
    }

    private void selectMainMenu(){
        Scanner scan = new Scanner(System.in);
        try {
            int mainMenuSelected = scan.nextInt();
            if(mainMenuSelected==1){
                merchantController.index();
            }
        }catch (InputMismatchException e){
            logger.error(e.toString());
        }
    }
}
