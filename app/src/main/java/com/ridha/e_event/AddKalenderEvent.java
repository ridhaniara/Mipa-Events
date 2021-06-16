package com.ridha.e_event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class AddKalenderEvent extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAddEvent, btnOneDay, btnMultipleDays;
    EditText edtNewEvent;
    CalendarView calendarView1, calendarView2;
    TextView txtCalendar, txtFirstDay, txtLastDay;
    String date1, date2;
    Boolean isOneDay;
    ImageButton btnLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kalender_event);

        edtNewEvent = (EditText) findViewById(R.id.edtNewEvent);
        txtFirstDay = (TextView) findViewById(R.id.txtFirstDay);
        txtLastDay = (TextView) findViewById(R.id.txtLastDay);
        btnAddEvent = (Button) findViewById(R.id.btnAddEvent);
        btnOneDay = (Button) findViewById(R.id.btnOneDay);
        btnMultipleDays = (Button) findViewById(R.id.btnMultipleDays);
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        myDB = new DatabaseHelper(this);
        calendarView1 = (CalendarView)findViewById(R.id.calendarView1);
        calendarView2 = (CalendarView)findViewById(R.id.calendarView2);
        calendarView2.setVisibility(View.INVISIBLE);
        txtCalendar = (TextView)findViewById(R.id.txtCalendar);

        isOneDay = true;

        calendarView1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date1 = (month + 1) + "/" + dayOfMonth + "/" + year;

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                String formattedDate = sdf.format(Date.parse(date1));

                txtFirstDay.setText( formattedDate );
            }
        });

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date2 = (month + 1) + "/" + dayOfMonth + "/" + year;

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                String formattedDate = sdf.format(Date.parse(date2));

                txtLastDay.setText( formattedDate );
            }
        });

        btnOneDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLastDay.setVisibility(View.INVISIBLE);
                txtLastDay.setText("");
                isOneDay = true;
            }
        });

        btnMultipleDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLastDay.setVisibility(View.VISIBLE);
                isOneDay = false;
            }
        });

        txtFirstDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView1.setVisibility(View.VISIBLE);
                calendarView2.setVisibility(View.INVISIBLE);
                txtCalendar.setText("Select First Day");
            }
        });

        txtLastDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView1.setVisibility(View.INVISIBLE);
                calendarView2.setVisibility(View.VISIBLE);
                txtCalendar.setText("Select Last Day");
            }
        });

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry;

                if (isOneDay){
                    newEntry = txtFirstDay.getText().toString() + ": " + edtNewEvent.getText().toString();
                } else {
                    newEntry = txtFirstDay.getText().toString() + " - " + txtLastDay.getText().toString() + ": " + edtNewEvent.getText().toString();
                }

                if(edtNewEvent.length()!= 0){
                    AddDataKalender(newEntry);
                    edtNewEvent.setText("");
                }else{
                    Toast.makeText(AddKalenderEvent.this, "Kolom nama event tidak boleh kosong!", Toast.LENGTH_LONG).show();
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

    public void AddDataKalender(String newEntry) {

        boolean insertData = myDB.addDataEvent(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data berhasil ditambahkan.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), KalenderEvent.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Ada sesuatu yang salah.", Toast.LENGTH_LONG).show();
        }
    }
}