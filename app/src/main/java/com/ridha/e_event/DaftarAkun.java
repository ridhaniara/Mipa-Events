package com.ridha.e_event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DaftarAkun extends AppCompatActivity {

    Button btnDaftar, btnGoLogin;
    EditText edtEmail, edtNama, edtNim, edtPassword;
    SharedPreferences sharedPreferences;

    public static String userNim, userPassword;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NIM = "nim";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);

        btnDaftar = (Button)findViewById(R.id.btnDaftar);
        btnGoLogin = (Button)findViewById(R.id.btnGoLogin);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtNama = (EditText)findViewById(R.id.edtNama);
        edtNim = (EditText)findViewById(R.id.edtNimDaftar);
        edtPassword = (EditText)findViewById(R.id.edtPasswordDaftar);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NIM, edtNim.getText().toString());
                editor.putString(KEY_PASSWORD, edtPassword.getText().toString());
                editor.apply();

//                userNim = edtNim.getText().toString();
//                userPassword = edtPassword.getText().toString();
                Toast.makeText(DaftarAkun.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Login.class);
//                intent.putExtra("userNim", userNim);
//                intent.putExtra("userPassword", userPassword);
                startActivity(intent);
            }
        });

        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

    public static String getUserNim(){
        return userNim;
    }

    public static String getUserPassword(){
        return userPassword;
    }
}