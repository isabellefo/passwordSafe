package com.gabrielle.passwordSafe;

import com.gabrielle.passwordSafe.passwords.Password;
import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.controllers.UserCreationDTO;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class UserTests {
    @Autowired
    UsersController usersController;

    private UserCreationDTO getTestUser() {
        return getTestUser("email@email.com");
    }

    private UserCreationDTO getTestUser(String email) {
        Password password = new Password();
        password.setName("email");
        password.setPassword("qwerty");
        return new UserCreationDTO("nome", email, "123456", password);

    }

    @Test
    void contextLoads() {}

    @Test
    void  testUserCreationSuccess() {
        UserCreationDTO user = getTestUser();
        ResponseEntity<Integer> response = usersController.saveUser(user);
        Integer savedUserId = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        boolean isValidId = savedUserId > 0;
        assertTrue(isValidId);
    }

    @Test
    void testUserCreationFails() {
        UserCreationDTO user = getTestUser("other@email.com");
        ResponseEntity<Integer> firstResp = usersController.saveUser(user);
        assertEquals(firstResp.getStatusCode(), HttpStatus.CREATED);
        ResponseEntity<Integer> secondResp = usersController.saveUser(user);
        assertEquals(secondResp.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(secondResp.getBody(),-1);
    }

    @Test
    void testUserRetrieval() {
        UserCreationDTO user = getTestUser();
        ResponseEntity<Integer> save = usersController.saveUser(user);
        Integer savedId = save.getBody();
        assertTrue(savedId > 0);
        ResponseEntity<UserDTO> res = usersController.findUser(savedId);
        UserDTO userDTO = res.getBody();
        assertEquals(userDTO.name, user.name);
    }

    @Test
    void testInvalidUser() {
        UserCreationDTO user = new UserCreationDTO(
                null,
                null,
                null,
                new Password()
        );
        ResponseEntity<Integer> res = usersController.saveUser(user);
        assertEquals(res.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(res.getBody(), -1);
    }
}
