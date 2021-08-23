package com.gabrielle.passwordSafe.passwords.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gabrielle.passwordSafe.passwords.Password;
import com.gabrielle.passwordSafe.passwords.repositories.IPasswordRepository;
import com.gabrielle.passwordSafe.passwords.services.IPasswordManagementService;

@RestController
@RequestMapping(value = "/password")
@CrossOrigin
public class PasswordController {
	
	@Autowired
	IPasswordManagementService passwordService;
	
	@Autowired
	IPasswordRepository passwordRepository;
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<Integer> savePassword(@RequestBody Password password) {

        Integer passwordId = passwordService.createPassword(password);
        if (passwordId > 0) {
            return new ResponseEntity(passwordId, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
	
    @GetMapping(value = "/{passwordId}", produces = "application/json")
    public ResponseEntity<PasswordDTO> findPassword(@PathVariable() Integer passwordId) {
        Password password = passwordService.findPassword(passwordId);
        if(password == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity(PasswordDTO.create(password), HttpStatus.OK);
    }
    
    @GetMapping(value = "/busca/{user}", produces = "application/json")
    public List<Password> findPasswordbyUser(@PathVariable("user") String user) {
        return passwordRepository.findByUserEmailOrName(user, user);
    }
	
    
}
