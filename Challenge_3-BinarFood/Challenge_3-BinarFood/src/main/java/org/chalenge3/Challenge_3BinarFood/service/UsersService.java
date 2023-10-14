package org.chalenge3.Challenge_3BinarFood.service;

import org.chalenge3.Challenge_3BinarFood.model.Users;
import org.chalenge3.Challenge_3BinarFood.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //Error buat create
    public void create(Users user) {
        usersRepository.save(user);
    }
}
