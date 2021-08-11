package com.gabrielle.passwordSafe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.controllers.UserDTO;
import com.gabrielle.passwordSafe.users.controllers.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class UserTests {
    @Autowired
    UsersController usersController;

    private User getTestUser() {
        return  new User("nome", "email@email.com", "123456");
    }

    @Test
    void contextLoads() {}

    @Test
    void  testUserCreation() {
        User user = getTestUser();
        ResponseEntity<Integer> response = usersController.saveUser(user);
        Integer savedUserId = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(savedUserId, 1);
    }

    @Test
    void testUserRetrieval() {
        User user = getTestUser();
        usersController.saveUser(user);

        ResponseEntity<UserDTO> res = usersController.findUser(user.getId());
        UserDTO userDTO = res.getBody();
        assertEquals(userDTO.name, user.getName());
    }
}
