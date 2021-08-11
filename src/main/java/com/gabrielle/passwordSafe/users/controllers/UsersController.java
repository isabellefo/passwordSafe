package com.gabrielle.passwordSafe.users.controllers;

import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.services.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UsersController {
    @Autowired
    IUserManagementService userManagementService;
    @GetMapping
    public ResponseEntity<String> home() {
       return new ResponseEntity("hello", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> saveUser(@RequestBody User user) {
        Integer userId = userManagementService.createUser(user);

        return new ResponseEntity(userId, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUser(@PathVariable() Integer userId) {
        User user = userManagementService.findUser(userId);

        return new ResponseEntity(UserDTO.create(user), HttpStatus.OK);
    }
}
