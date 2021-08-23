package com.gabrielle.passwordSafe.users.services;

import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.controllers.UserCreationDTO;

public interface IUserManagementService {
    Integer createUser(UserCreationDTO user);
    User findUser(Integer userId);
}

