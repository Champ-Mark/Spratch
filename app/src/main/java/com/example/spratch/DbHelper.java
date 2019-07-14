package com.example.spratch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "records.db";
    public static final int DATABASE_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE " +
                Table.column.table_name + " (" +
                Table.column._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.column.column_lap1 + " TEXT NOT NULL, " +
                Table.column.column_lap2 + " TEXT NOT NULL, " +
                Table.column.column_lap3 + " TEXT NOT NULL, " +
                Table.column.column_lap4 + " TEXT NOT NULL, " +
                Table.column.column_timestamp + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table.column.table_name);
        onCreate(db);
    }
}
