package com.example.testenc.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testenc.Functions.Decrypt;
import com.example.testenc.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Decryption extends AppCompatActivity {

    Button decBtn;
    EditText etEncText;
    EditText etSecretKey;
    EditText ivText;
    TextView tvOrjText;

    MessageDigest messageDigest = null;
    byte[] digest = new byte[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decryption);

        etEncText = findViewById(R.id.etEncText);
        ivText = findViewById(R.id.etAnahtar);
        etSecretKey = findViewById(R.id.etSecretKey);
        tvOrjText = findViewById(R.id.tvOrjText);
        decBtn = findViewById(R.id.decBtn);

        final DB db = DB.getInstance();
        etSecretKey.setText(db.getKEY());
        ivText.setText(db.getIV());
        etEncText.setText(db.getENC_TEXT());

        decBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TextUtils.isEmpty(etEncText.getText()) || (TextUtils.isEmpty(ivText.getText())))) {
                    Toast.makeText(getApplicationContext(), "Fill empty fields.", Toast.LENGTH_SHORT).show();
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

                try {
                    byte[] encText = decoderfun(db.getENC_TEXT());
                    String decryptedText = Decrypt.decrypt(encText, db.getKEY(), digest);
                    tvOrjText.setText(decryptedText);

                } catch (Exception e) {
                    Log.e("tag", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });

    }

    private static byte[] decoderfun(String enval) {
        return Base64.decode(enval, Base64.DEFAULT);
    }
}
