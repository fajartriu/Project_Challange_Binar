package project.Challenge_4BinarFood.service;

import org.springframework.stereotype.Service;
import project.Challenge_4BinarFood.model.users.CreateUsersRequest;
import project.Challenge_4BinarFood.response.UsersResponse;

import java.util.*;

@Service
public interface UsersService {
    UsersResponse createUsers(CreateUsersRequest request);
    void deleteUser(UUID userId);
    UsersResponse updateUser(UUID userId, Map<String, Optional> field);
    List<UsersResponse> getAllUsers();
}
