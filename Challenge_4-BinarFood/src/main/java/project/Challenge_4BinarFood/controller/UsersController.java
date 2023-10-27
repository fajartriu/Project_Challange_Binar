package project.Challenge_4BinarFood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import project.Challenge_4BinarFood.model.users.CreateUsersRequest;
import project.Challenge_4BinarFood.response.ProductResponse;
import project.Challenge_4BinarFood.response.ProductUpdateResponse;
import project.Challenge_4BinarFood.response.UsersResponse;
import project.Challenge_4BinarFood.response.WebResponse;
import project.Challenge_4BinarFood.service.UsersService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    TemplateEngine templateEngine;

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UsersResponse> createUsers(@RequestBody CreateUsersRequest request){
        try {
            UsersResponse users = usersService.createUsers(request);
            return WebResponse.<UsersResponse>builder().countRecord(1).data(users).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<UsersResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @DeleteMapping(
            path = "/api/users/deleteUsers/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> deleteUsers(@PathVariable UUID userId){
        try {
            usersService.deleteUser(userId);
            return WebResponse.<String>builder().data("Deleted").messages("Success").build();
        }catch (Exception e){
            return WebResponse.<String>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @PatchMapping(
            path = "/api/users/updateUser/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UsersResponse> updateUser(@PathVariable UUID userId, @RequestBody Map<String, Optional> field){
        try {
            UsersResponse usersResponse = usersService.updateUser(userId, field);
            return WebResponse.<UsersResponse>builder().countRecord(1).data(usersResponse).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<UsersResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/users/getAllUsers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<UsersResponse>> getAllUsers(){
        try {
            List<UsersResponse> allUsers = usersService.getAllUsers();
            return WebResponse.<List<UsersResponse>>builder().countRecord(allUsers.size()).data(allUsers).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<UsersResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }
}
