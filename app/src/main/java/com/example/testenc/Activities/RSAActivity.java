package com.example.testenc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testenc.Functions.GenerateKeys;
import com.example.testenc.Functions.RSAOperations;
import com.example.testenc.R;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAActivity extends AppCompatActivity {

    Button generateBtn, encryptBtn, decryptBtn;
    TextView publicTV, privateTV, encryptedTV, decryptedTV;
    EditText textET;

    final GenerateKeys generateKeys = GenerateKeys.getInstance();

    String text = "";
    String encryptedText = "";
    String decryptedText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);

        generateBtn = findViewById(R.id.generate);
        encryptBtn = findViewById(R.id.encryptRSA);
        decryptBtn = findViewById(R.id.decryptRSA);

        publicTV = findViewById(R.id.publicKey);
        privateTV = findViewById(R.id.privateKey);
        encryptedTV = findViewById(R.id.encryptedText);
        decryptedTV = findViewById(R.id.decryptedText);

        textET = findViewById(R.id.originalText);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    generateKeys.initGenerator(1024);
                    generateKeys.createKeys();
                    publicTV.setText(generateKeys.getPublicKeyAsStr());
                    privateTV.setText(generateKeys.getPrivateKeyAsStr());
                } catch (NoSuchAlgorithmException e) {
                    Log.e("tag 0", e.getLocalizedMessage());
                } catch (NoSuchProviderException e) {
                    Log.e("tag 1", e.getLocalizedMessage());
                }
            }
        });

        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textET.getText().toString().equals("")) {
                    return;
                }
                text = textET.getText().toString().trim();
                try {
                    RSAOperations rsaOperations = new RSAOperations();

                    try {
                        encryptedText = rsaOperations.encrypt(text, generateKeys.getPublicKey());
                        encryptedTV.setText(encryptedText);
                    } catch (NoSuchAlgorithmException e) {
                        Log.e("tag 4", e.getLocalizedMessage());
                    } catch (NoSuchPaddingException e) {
                        Log.e("tag 5", e.getLocalizedMessage());
                    } catch (UnsupportedEncodingException e) {
                        Log.e("tag 6", e.getLocalizedMessage());
                    } catch (IllegalBlockSizeException e) {
                        Log.e("tag 7", e.getLocalizedMessage());
                    } catch (BadPaddingException e) {
                        Log.e("tag 8", e.getLocalizedMessage());
                    } catch (InvalidKeyException e) {
                        Log.e("tag 9", e.getLocalizedMessage());
                    }

                } catch (NoSuchAlgorithmException e) {
                    Log.e("tag 2", e.getLocalizedMessage());
                } catch (NoSuchPaddingException e) {
                    Log.e("tag 3", e.getLocalizedMessage());
                }
            }
        });

        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (encryptedText.equals("")) {
                    return;
                }
                try {
                    RSAOperations rsaOperations = new RSAOperations();

                    try {
                        decryptedText = rsaOperations.decrypt(encryptedText, generateKeys.getPrivateKey());
                        decryptedTV.setText(decryptedText);
                    } catch (NoSuchAlgorithmException e) {
                        Log.e("tag 10", e.getLocalizedMessage());
                    } catch (NoSuchPaddingException e) {
                        Log.e("tag 11", e.getLocalizedMessage());
                    } catch (UnsupportedEncodingException e) {
                        Log.e("tag 12", e.getLocalizedMessage());
                    } catch (IllegalBlockSizeException e) {
                        Log.e("tag 13", e.getLocalizedMessage());
                    } catch (BadPaddingException e) {
                        Log.e("tag 14", e.getLocalizedMessage());
                    } catch (InvalidKeyException e) {
                        Log.e("tag 15", e.getLocalizedMessage());
                    }

                } catch (NoSuchAlgorithmException e) {
                    Log.e("tag 2", e.getLocalizedMessage());
                } catch (NoSuchPaddingException e) {
                    Log.e("tag 3", e.getLocalizedMessage());
                }
            }
        });

    }
}
