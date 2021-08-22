package com.gabrielle.passwordSafe.passwords.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabrielle.passwordSafe.passwords.Password;
import com.gabrielle.passwordSafe.passwords.repositories.IPasswordRepository;
import com.gabrielle.passwordSafe.security.services.SecurityService;

@Service("passwordService")
public class PasswordManagementService implements IPasswordManagementService{

	@Autowired
	IPasswordRepository passwordRepository;

	@Autowired
	SecurityService securityService;
	
	@Transactional
	@Override
	public Integer createPassword(Password password) {
		String masterPassword = password.getUser().getMasterPassword();
		if(masterPassword == null || masterPassword.isEmpty()) {
			return -1;
		}

		String encryptedPassword = securityService.encryptAccountPassword(password.getPassword(), masterPassword);
		password.setPassword(encryptedPassword);

        return passwordRepository.save(password).getId();
	}
	

	@Override
	public Password findPassword(Integer passwordId) {
		return passwordRepository.findById(passwordId);
	}

}
