package com.ridha.e_event;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Kontak extends AppCompatActivity {

    DatabaseHelper myDB;
    SearchView searchView;
    ListView myKontakList;
    Button btnTambahBaru;
    ImageButton btnLeftKontak, btnRightKontak;
    Cursor data;

    ArrayList<String> list;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        searchView = (SearchView)findViewById(R.id.searchView);
        myKontakList = (ListView)findViewById(R.id.kontakList);
        btnTambahBaru = (Button)findViewById(R.id.btnTambahBaru);
        btnLeftKontak = (ImageButton) findViewById(R.id.btnLeftKontak);
        btnRightKontak = (ImageButton) findViewById(R.id.btnRightKontak);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListKontak();
        if(data.getCount() == 0){
            Toast.makeText(this, "List kontak masih kosong!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                myKontakList.setAdapter(listAdapter);
            }
        }

//        list = new ArrayList<String>();
//        data = myDB.getListContents();

//        list.add("Ketua BEM Fmipa: Miranda Fasya " +
//                "081212233445");
//        list.add("Sekretaris umum Fmipa: Ridha Niara " +
//                "082234544422");
//        list.add("Ketua Himpunan Informatika: T. Adinata " +
//                "082387664733");
//        list.add("Bendahara umum Fmipa: Jihan Salsabila " +
//                "081265223422");
//        list.add("Ketua Himpunan Fisika: Alfahri Abraham " +
//                "082235442788");
//        list.add("Ketua umum Kominfo: Niko Munara " +
//                "082217663288");
//        list.add("Wakil Ketua BEM Fmipa: Cut Glenca " +
//                "0812276554590");

//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        myKontakList.setAdapter(adapter);

        btnTambahBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddKontak.class);
                startActivity(intent);
                finish();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                listAdapter.getFilter().filter(s);

                return false;
            }
        });

        btnLeftKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRightKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Logout.class);
                startActivity(intent);
            }
        });
    }
}