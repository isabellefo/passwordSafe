package com.gabrielle.passwordSafe.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service("securityService")
public class SecurityService implements ISecurityService {
    @Autowired
    BCryptPasswordEncoder bcrypt;

    @Autowired
    VigenereCipher cipher;

    @Override
    public String hashMasterPassword(String masterPassword) {
        return bcrypt.encode(masterPassword);
    }

    @Override
    public boolean masterPasswordMatchesHash(String masterPassword, String hash) {
        return bcrypt.matches(masterPassword, hash);
    }

    @Override
    public String encryptAccountPassword(String password, String masterPassword) {
        return cipher.encrypt(password, masterPassword);
    }

    @Override
    public String decryptAccountPassword(String password, String masterPassword) {
        return cipher.decrypt(password, masterPassword);
    }

}
