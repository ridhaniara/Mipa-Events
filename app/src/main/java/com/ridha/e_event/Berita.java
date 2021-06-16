package com.ridha.e_event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Berita extends AppCompatActivity {

    ImageButton btnLeftBerita, btnRightBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        btnLeftBerita = (ImageButton) findViewById(R.id.btnLeftBerita);
        btnRightBerita = (ImageButton) findViewById(R.id.btnRightBerita);

        btnLeftBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRightBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Logout.class);
                startActivity(intent);
            }
        });
    }
}