package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Data {
    private Data(){}
    public static List<Optional<MenuItem>> menuFoods = new ArrayList<>();
    public static List<Optional<MenuItem>> menuDrinks = new ArrayList<>();
    public static List<Optional<MenuItem>> menuItems = new ArrayList<>();
    public static List<Optional<OrderItem>> orderItems = new ArrayList<>();
    public static List<Optional<Notes>> notesMakanan = new ArrayList<>();
    public static List<Optional<Notes>> notesMinuman = new ArrayList<>();
}