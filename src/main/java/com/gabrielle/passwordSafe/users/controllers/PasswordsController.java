package com.gabrielle.passwordSafe.users.controllers;

import com.gabrielle.passwordSafe.passwords.Password;
import com.gabrielle.passwordSafe.passwords.controllers.PasswordDTO;
import com.gabrielle.passwordSafe.passwords.services.IPasswordManagementService;
import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.services.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/users/{userId}/password")
@CrossOrigin
public class PasswordController {
    @Autowired
    IUserManagementService userManagementService;

    @Autowired
    IPasswordManagementService passwordManagementService;

    @PostMapping(value = "")
    public Integer addPassword(@PathVariable("userId") Integer userId, @RequestBody Password password) {
        User user = userManagementService.findUser(userId);
        if(user != null) {
            password.setUser(user);
            return passwordManagementService.createPassword(password);
        }
        return -1;
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<PasswordDTO>> findPasswordsByUser(@PathVariable("userId") Integer userId) {
        List<Password> passwords = passwordManagementService.findUserPasswords(userId);
        List<PasswordDTO> dtos = new ArrayList<>();
        for(Password pwd : passwords) {
            dtos.add(PasswordDTO.create(pwd));
        }
        return new ResponseEntity<List<PasswordDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{passwordName}", produces = "application/json")
    public ResponseEntity<PasswordDTO> findPasswordByUser(
            @PathVariable("userId") Integer userId,
            @PathVariable("passwordName") String passwordName
    ) {
        Password pwd = passwordManagementService.findUserPassword(userId, passwordName);
        return new ResponseEntity<PasswordDTO>(PasswordDTO.create(pwd), HttpStatus.OK);
    }
}
