package com.gabrielle.passwordSafe.passwords.services;

import com.gabrielle.passwordSafe.passwords.Password;

import java.util.List;

public interface IPasswordManagementService {
	Integer createPassword(Password password);
	Password findPassword(Integer passwordId);
	List<Password> findUserPasswords(Integer userId);
}
