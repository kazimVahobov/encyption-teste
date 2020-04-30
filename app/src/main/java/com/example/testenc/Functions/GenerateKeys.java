package com.example.testenc.Functions;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKeys {

    private static GenerateKeys instance;

    private KeyPairGenerator generator;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static synchronized GenerateKeys getInstance() {
        if (instance == null) {
            instance = new GenerateKeys();
        }
        return instance;
    }

    public void initGenerator(int keyLength) throws NoSuchAlgorithmException, NoSuchProviderException {
        generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keyLength);
    }

    public void createKeys() {
        KeyPair pair = generator.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getPrivateKeyAsStr() {
        return privateKey.toString();
    }

    public String getPublicKeyAsStr() {
        return publicKey.toString();
    }
}
