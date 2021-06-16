package com.ridha.e_event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edtNimLogin, edtPasswordLogin;
    Button btnLogin;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NIM = "nim";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        edtNimLogin = (EditText)findViewById(R.id.edtNimLogin);
        edtPasswordLogin = (EditText)findViewById(R.id.edtPasswordLogin);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String nim = sharedPreferences.getString(KEY_NIM, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);

        if(nim != null){
            edtNimLogin.setText(nim);
        }

//        edtNimLogin.setText(getIntent().getStringExtra("userNim"));
//        edtPasswordLogin.setText(getIntent().getStringExtra("userPassword"));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtNimLogin.getText().toString().equals(nim) && edtPasswordLogin.getText().toString().equals(password)){
                    Toast.makeText(Login.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MenuUtama.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "NIM atau Password salah!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}