package com.gabrielle.passwordSafe.users.services;

import com.gabrielle.passwordSafe.security.services.ISecurityService;
import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Service("userManagementService")
public class UserManagementService implements IUserManagementService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    ISecurityService securityService;

    @Transactional
    @Override
    public Integer createUser(User user) {
        boolean userExists = isUserRegistered(user);
        if(userExists || !user.isValid()) {
            return -1;
        }

        String hash = securityService.hashMasterPassword(user.getMasterPassword());
        user.setMasterPassword(hash);

        user = userRepository.save(user);
        return user.getId();
    }

    private boolean isUserRegistered(User userToBeSaved) {
        return userRepository.findByEmail(userToBeSaved.getEmail()) != null;
    }

    @Override
    public User findUser(@PathVariable Integer userId) {
        return userRepository.findById(userId);
    }
}
