package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private Data(){}
    public static List<MenuItem> menuItems = new ArrayList<>();
    public static List<OrderItem> orderItems = new ArrayList<>();
    public static List<Note> notesMakanan = new ArrayList<>();
    public static List<Note> notesMinuman = new ArrayList<>();
    public static List<OrderNotes> notes = new ArrayList<>();
}