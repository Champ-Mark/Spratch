package com.example.spratch;

import android.provider.BaseColumns;

public class Table {
    private Table(){

    }
    public class column implements BaseColumns{
        public static final String table_name = "records";
        public static final String column_lap1 = "lap1";
        public static final String column_lap2 = "lap2";
        public static final String column_lap3 = "lap3";
        public static final String column_lap4 = "lap4";
        public static final String column_timestamp = "timestamp";
    }
}
