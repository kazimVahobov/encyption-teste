package com.example.testenc.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testenc.Functions.Encrypt;
import com.example.testenc.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption extends AppCompatActivity {

    EditText etOrjText;
    TextView tvSecretKey;

    TextView encText;
    TextView ivText;

    Button encBtn;

    KeyGenerator keyGenerator;
    SecretKey secretKey;
    byte[] secretKeyen;
    String strSecretKey;
//    byte[] IV = new byte[16];
    byte[] cipherText;
//    SecureRandom random;
    MessageDigest messageDigest = null;
    byte[] digest = new byte[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encryption);

        etOrjText = findViewById(R.id.etOrjText);
        tvSecretKey = findViewById(R.id.secretkey);
        encText = findViewById(R.id.sonuc);
        ivText = findViewById(R.id.anahtar);
        encBtn = findViewById(R.id.encBtn);

        final DB db = DB.getInstance();
        tvSecretKey.setText(db.getKEY());

        Long tsLong = System.currentTimeMillis();
        final String ts = tsLong.toString();
        db.setIV(ts);
        ivText.setText(ts);

        encBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etOrjText.getText())) {
                    Toast.makeText(getApplicationContext(), "Fill empty field.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.reset();
                    messageDigest.update(db.getIV().getBytes());
                    digest = messageDigest.digest();
                } catch (NoSuchAlgorithmException e) {
                    Log.e("tag md", e.getLocalizedMessage());
                }

                db.setTEXT(etOrjText.getText().toString().trim());
                secretKey = new SecretKeySpec(db.getKEY().getBytes(), "AES");

                try {
                    cipherText = Encrypt.encrypt(db.getTEXT().getBytes(), secretKey, digest);

                    db.setENC_TEXT(encoderfun(cipherText));
                    encText.setText(db.getENC_TEXT());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String encoderfun(byte[] decval) {
        return Base64.encodeToString(decval, Base64.DEFAULT);
    }
}
