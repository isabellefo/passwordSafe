package com.gabrielle.passwordSafe;

import com.gabrielle.passwordSafe.passwords.Password;
import com.gabrielle.passwordSafe.passwords.controllers.PasswordController;
import com.gabrielle.passwordSafe.passwords.controllers.PasswordDTO;
import com.gabrielle.passwordSafe.encryption.SecurityService;
import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.controllers.UserCreationDTO;
import com.gabrielle.passwordSafe.users.services.UserManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PasswordTests {
    @Autowired
    PasswordController passwordController;

    @Autowired
    UserManagementService userService;

    @Autowired
    SecurityService securityService;

    private User userTest;

    @Test
    void contextLoads() {}

    void createUser() {
        Password pwd = new Password();
        pwd.setPassword("qwerty");
        pwd.setName("name");
        UserCreationDTO dto = new UserCreationDTO("test", "teste@gmail.com", "123456", pwd);
        userTest = userService.createUser(dto);
    }

    private Password getTestPassword() {
        return new Password("password", "blablabla", userTest);
    }

    @Test
    void testPasswordCreationSuccess() {
        createUser();

        Password password = getTestPassword();
        ResponseEntity<Integer> response = passwordController.savePassword(password);
        Integer passwordId = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        boolean isValidId = passwordId > 0;
        assertTrue(isValidId);
    }
    
    @Test
    void testPasswordCreationWithoutMasterPassword() {
        createUser();

        Password password = getTestPassword();
        password.getUser().setMasterPassword(null);
        ResponseEntity<Integer> saved = passwordController.savePassword(password);
        assertTrue(saved.getStatusCode() == HttpStatus.BAD_REQUEST);
    }

    @Test
    void testPasswordRetrieval() {
        createUser();

        Password password = getTestPassword();
        String encryptedPassword = securityService.encryptAccountPassword(password.getPassword(), userTest.getMasterPassword());

        ResponseEntity<Integer> saved = passwordController.savePassword(password);
        assertTrue(saved.getBody() > 0);

        ResponseEntity<PasswordDTO> response = passwordController.findPassword(saved.getBody());
        PasswordDTO passwordDTO = response.getBody();
        assertEquals(encryptedPassword, passwordDTO.password);
    }
}

















