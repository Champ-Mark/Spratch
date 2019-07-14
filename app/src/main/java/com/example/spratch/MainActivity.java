package com.example.spratch;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private Button button_start;
    private Button button_reset;
    private Button button_lap;
    private Button button_save;
    private Button button_records;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private boolean running;
    private long pause;
    private int count;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(this);
        database = dbHelper.getWritableDatabase();
        chronometer = findViewById(R.id.cnm);
        button_start=findViewById(R.id.start);
        button_reset=findViewById(R.id.reset);
        button_lap=findViewById(R.id.lap);
        button_save=findViewById(R.id.save);
        button_records=findViewById(R.id.records);
        tv1=findViewById(R.id.time1);
        tv2=findViewById(R.id.time2);
        tv3=findViewById(R.id.time3);
        tv4=findViewById(R.id.time4);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            start();
            }
        });
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        button_lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lap();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        button_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              records();
            }
        });

    }
    public void start(){
    if(!running) {
        chronometer.setBase(SystemClock.elapsedRealtime() - pause);
        chronometer.start();
        running = true;
        button_start.setText("Pause");
    }
    else {
        chronometer.stop();
        running = false;
        button_start.setText("RESUME");
        pause = SystemClock.elapsedRealtime() - chronometer.getBase();

    }
    }
    public void reset(){
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        pause = 0;
        button_start.setText("start");
        running = false;
        count=0;
    }
    public void lap() {
        count++;
        String s = (String) chronometer.getText();
        switch (count){
        case 1: tv1.setText(s);
        break;
        case 2: tv2.setText(s);
        break;
        case 3: tv3.setText(s);
        break;
        case 4: tv4.setText(s);
        count=0;
        break;
    }
    }
    public void save() {
        String s1 = tv1.getText().toString();
        String s2 = tv2.getText().toString();
        String s3 = tv3.getText().toString();
        String s4 = tv4.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(Table.column.column_lap1,s1);
        cv.put(Table.column.column_lap2,s2);
        cv.put(Table.column.column_lap3,s3);
        cv.put(Table.column.column_lap4,s4);
        database.insert(Table.column.table_name,null,cv);



    }
    public void records() {
        Intent intent = new Intent(this,Records.class);
        startActivity(intent);
    }


}

