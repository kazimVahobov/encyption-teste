package com.example.testenc.Functions;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAOperations {
    private Cipher cipher;

    public RSAOperations() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
    }

    public String encrypt(String msg, PublicKey key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.encode(bytes, Base64.DEFAULT));
    }

    public String decrypt(String msg, PrivateKey key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encBytes = Base64.decode(msg, Base64.DEFAULT);
        byte[] decBytes = cipher.doFinal(encBytes);
        return new String(decBytes);
    }
}
