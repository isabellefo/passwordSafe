package com.gabrielle.passwordSafe.users.services;

import com.gabrielle.passwordSafe.passwords.Password;
import com.gabrielle.passwordSafe.passwords.services.IPasswordManagementService;
import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.controllers.UserCreationDTO;
import com.gabrielle.passwordSafe.users.repositories.IUserRepository;
import com.gabrielle.passwordSafe.users.services.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.SystemException;
import javax.transaction.Transactional;

@Service("userManagementService")
public class UserManagementService implements IUserManagementService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    IPasswordManagementService passwordService;

    @Transactional
    @Override
    public Integer createUser(UserCreationDTO userDTO) {
        boolean userExists = isUserRegistered(userDTO);
        if(userExists || !userDTO.isValid()) {
            return -1;
        }
        User user = User.create(userDTO);
        user = userRepository.save(user);
        userDTO.password.setUser(user);
        Integer created = passwordService.createPassword(userDTO.password);
        if (created > 0) {
            return user.getId();
        }
        return  -1;
    }

    private boolean isUserRegistered(UserCreationDTO userToBeSaved) {
        return userRepository.findByEmail(userToBeSaved.email) != null;
    }

    @Override
    public User findUser(@PathVariable Integer userId) {
        return userRepository.findById(userId);
    }
}
