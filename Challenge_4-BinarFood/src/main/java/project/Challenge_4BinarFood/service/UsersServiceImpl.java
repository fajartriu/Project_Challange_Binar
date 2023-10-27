package project.Challenge_4BinarFood.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.Challenge_4BinarFood.entity.Users;
import project.Challenge_4BinarFood.model.users.CreateUsersRequest;
import project.Challenge_4BinarFood.response.UsersResponse;
import project.Challenge_4BinarFood.respository.UsersRepository;

import java.util.*;

@Service
public class UsersServiceImpl implements UsersService{
    private final static Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final UsersRepository usersRepository;
    private final ValidationService validationService;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, ValidationService validationService) {
        this.usersRepository = usersRepository;
        this.validationService = validationService;
    }

    @Transactional
    @Override
    public UsersResponse createUsers(CreateUsersRequest request) {
        try {
            validationService.validate(request);
            Users users = new Users();
            users.setId(UUID.randomUUID());
            users.setUsername(request.getUserName());
            users.setEmailAddress(request.getEmailAddress());
            users.setPassword(request.getPassword());

            usersRepository.createUsers(users.getId(), users.getUsername(), users.getEmailAddress(), users.getPassword());
            logger.info("Success store data users");

            return UsersResponse.builder()
                    .id(users.getId())
                    .username(users.getUsername())
                    .emailAddress(users.getEmailAddress())
                    .password(users.getPassword())
                    .build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteUser(UUID userId) {
        try {
            Users users = usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));
            usersRepository.deleteUsers(users.getId());
            logger.info("Success delete user");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Transactional
    @Override
    public UsersResponse updateUser(UUID userId, Map<String, Optional> field) {
        try {
            Users users = usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));

            for (Map.Entry<String, Optional> m : field.entrySet()){
                String keyFromMap = m.getKey();
                logger.debug(keyFromMap);

                if (keyFromMap.equals("userName")){
                    String obj = m.getValue().toString().replaceAll("^Optional\\[(.*?)\\]$", "$1");
                    logger.debug(obj);
                    users.setUsername(obj);
                }

                if (keyFromMap.equals("emailAddress")){
                    String obj = m.getValue().toString().replaceAll("^Optional\\[(.*?)\\]$", "$1");
                    logger.debug(obj);
                    users.setEmailAddress(obj);
                }

                if (keyFromMap.equals("password")){
                    String obj = m.getValue().toString().replaceAll("^Optional\\[(.*?)\\]$", "$1");
                    logger.debug(obj);
                    users.setPassword(obj);
                }
            }
            usersRepository.updateUser(users.getId(), users.getUsername(), users.getEmailAddress(), users.getPassword());
            logger.info("Success update user");

            return UsersResponse.builder()
                    .id(users.getId())
                    .username(users.getUsername())
                    .emailAddress(users.getEmailAddress())
                    .password(users.getPassword())
                    .build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<UsersResponse> getAllUsers() {
        List<Users> all = usersRepository.findAll();
        List<UsersResponse> usersResponses = new ArrayList<>();

        if (!all.isEmpty()){
            for (Users users: all){
                usersResponses.add(new UsersResponse(users.getId(), users.getUsername(), users.getEmailAddress(), users.getPassword()));
                logger.debug(usersResponses.toString());
            }
        }

        if (!usersResponses.isEmpty()){
            logger.info("Data response success " + usersResponses);
            return usersResponses;
        }
        logger.error("Response Data is empty: " + Collections.emptyList());
        return Collections.emptyList();
    }
}
