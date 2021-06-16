package com.ridha.e_event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddKontak extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd;
    EditText edtNewKontak;
    ImageButton btnLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kontak);

        edtNewKontak = (EditText) findViewById(R.id.edtNewKontak);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = edtNewKontak.getText().toString();
                if(edtNewKontak.length()!= 0){
                    AddDataKontak(newEntry);
                    edtNewKontak.setText("");
                }else{
                    Toast.makeText(AddKontak.this, "Kolom kontak tidak boleh kosong!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KalenderEvent.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void AddDataKontak(String newEntry) {

        boolean insertData = myDB.addDataKontak(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data berhasil ditambahkan.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Kontak.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Ada sesuatu yang salah.", Toast.LENGTH_LONG).show();
        }
    }
}