package com.gabrielle.passwordSafe.users.services;

import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.repositories.IUserRepository;
import com.gabrielle.passwordSafe.users.services.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserManagementService implements IUserManagementService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public Long createUser(User user) {
        userRepository.save(user);
        return Long.valueOf(0);
    }
}
