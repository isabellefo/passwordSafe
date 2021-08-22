package com.gabrielle.passwordSafe.security.services;

public interface ICipherAlgorithm {
    String encrypt(String password, String masterPassword);
    String decrypt(String password, String masterPassword);
}
