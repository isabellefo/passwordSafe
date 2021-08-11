package com.gabrielle.passwordSafe.users.services;

import com.gabrielle.passwordSafe.users.User;
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

    @Transactional
    @Override
    public Integer createUser(User user) {
        System.out.println(user.getName());
        System.out.println(user.getId());
        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    public User findUser(@PathVariable Integer userId) {
        return userRepository.findById(userId);
    }
}
