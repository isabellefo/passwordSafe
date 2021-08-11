package com.gabrielle.passwordSafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.controllers.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class UserTests {
    @Autowired
    UsersController usersController;

    @Test
    void contextLoads() {}

    @Test
    void  testUserCreation() {
        User user = new User("nome", "email@email.com", "123456");
        ResponseEntity<Long> response = usersController.saveUser(user);
        Long savedUserId = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(savedUserId, 1);
    }
}
