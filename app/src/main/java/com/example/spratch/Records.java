package com.example.spratch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class Records extends AppCompatActivity {

    private RecyclerView recyclerView;
    private adaptor ad;
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        dbHelper = new DbHelper(this);
        database = dbHelper.getWritableDatabase();
        recyclerView = findViewById(R.id.records);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ad = new adaptor(this,getAllItems());
        recyclerView.setAdapter(ad);
        ad.swap_cursor(getAllItems());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            removeitem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

    }
    private Cursor getAllItems() {
        return database.query(
                Table.column.table_name,
                null,
                null,
                null,
                null,
                null,
                Table.column.column_timestamp + " DESC"
        );
    }
    private void removeitem(long id) {
    database.delete(Table.column.table_name,Table.column._ID + "=" +id,null);
    ad.swap_cursor(getAllItems());
    }
}
