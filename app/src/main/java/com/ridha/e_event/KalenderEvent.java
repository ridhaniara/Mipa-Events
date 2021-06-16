package com.ridha.e_event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class KalenderEvent extends AppCompatActivity {

    DatabaseHelper myDB;
    ListView myKalenderList;
    Button btnTambahKalender;
    ImageButton btnLeftKalender, btnRightKalender;
    Cursor data;

    ArrayList<String> list;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender_event);

        myKalenderList = (ListView)findViewById(R.id.kalenderList);
        btnTambahKalender = (Button)findViewById(R.id.btnTambahKalender);
        btnLeftKalender = (ImageButton) findViewById(R.id.btnLeftKalender);
        btnRightKalender = (ImageButton) findViewById(R.id.btnRightKalender);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListKalender();
        if(data.getCount() == 0){
            Toast.makeText(this, "List event masih kosong!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                myKalenderList.setAdapter(listAdapter);
            }
        }

        btnTambahKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddKalenderEvent.class);
                startActivity(intent);
                finish();
            }
        });

        btnLeftKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRightKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Logout.class);
                startActivity(intent);
            }
        });
    }
}