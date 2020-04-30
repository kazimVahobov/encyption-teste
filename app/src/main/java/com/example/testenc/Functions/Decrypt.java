package com.example.testenc.Functions;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
    public static String decrypt(byte[] cipherText, String key, byte[] IV) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");

            IvParameterSpec ivSpec = new IvParameterSpec(IV);

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            return new String(cipher.doFinal(cipherText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
