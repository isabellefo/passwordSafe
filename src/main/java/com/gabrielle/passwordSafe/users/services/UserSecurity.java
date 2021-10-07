package com.gabrielle.passwordSafe.users.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.gabrielle.passwordSafe.users.User;
import com.gabrielle.passwordSafe.users.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userSecurity")
public class UserSecurity implements IUserSecurity {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        UserDetails details = getSpringUserBuilder().username(email).password(user.getMasterPassword())
                .authorities("ROLE_ADMIN")
                .build();
        System.out.println("[details] "+ details.getUsername()+ "\t"+ details.getPassword());
        return details;
    }

    public org.springframework.security.core.userdetails.User.UserBuilder getSpringUserBuilder() {
        return org.springframework.security.core.userdetails.User.builder();
    }
}
