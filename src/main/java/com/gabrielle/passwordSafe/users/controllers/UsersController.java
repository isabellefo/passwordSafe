package com.gabrielle.passwordSafe.users.controllers;

import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.services.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UsersController {
    @Autowired
    IUserManagementService userManagementService;

    public ResponseEntity<Long> saveUser(User user) {
        Long userId = userManagementService.createUser(user);

        return new ResponseEntity(userId, HttpStatus.CREATED);
    }
}
