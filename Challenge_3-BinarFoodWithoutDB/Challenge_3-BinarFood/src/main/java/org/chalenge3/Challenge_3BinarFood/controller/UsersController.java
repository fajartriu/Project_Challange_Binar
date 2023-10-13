package org.chalenge3.Challenge_3BinarFood.controller;

import org.chalenge3.Challenge_3BinarFood.model.Users;
import org.chalenge3.Challenge_3BinarFood.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersController {
    private final UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    public void initiateUsers(){
        Users fajar = new Users("Fajar", "fajartu@gmail.com", "123456");
        Users kokom = new Users("Kokom", "kokom@gmail.com", "654321");

        List<Optional<Users>> user = new ArrayList<>();

        user.add(Optional.of(fajar));
        user.add(Optional.of((kokom)));

        for (Optional<Users> users: user){
            add(users);
        }
    }

    public void add(Optional<Users> user){
        usersService.create(user);
    }

}
