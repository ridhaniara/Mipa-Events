package com.ridha.e_event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MenuUtama extends AppCompatActivity {

    Button btnBerita, btnKalender, btnKontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        btnBerita = (Button)findViewById(R.id.btnBerita);
        btnKalender = (Button)findViewById(R.id.btnKalender);
        btnKontak = (Button)findViewById(R.id.btnKontak);

        btnBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Berita.class);
                startActivity(intent);
            }
        });

        btnKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KalenderEvent.class);
                startActivity(intent);
            }
        });

        btnKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Kontak.class);
                startActivity(intent);
            }
        });
    }
}