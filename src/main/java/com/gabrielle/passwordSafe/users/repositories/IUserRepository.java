package com.gabrielle.passwordSafe.users.repositories;

import com.gabrielle.passwordSafe.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}
