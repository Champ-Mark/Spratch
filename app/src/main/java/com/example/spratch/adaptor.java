package com.example.spratch;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class adaptor extends RecyclerView.Adapter<adaptor.records_view_holder> {
    private Context mcontext;
    private Cursor mcursor;
    public adaptor(Context context,Cursor cursor){
    mcontext=context;
    mcursor=cursor;
    }

    public class records_view_holder extends RecyclerView.ViewHolder{
        public TextView t1;
        public TextView t2;
        public TextView t3;
        public TextView t4;

        public records_view_holder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);
            t4 = itemView.findViewById(R.id.t4);
        }
    }

    @NonNull
    @Override
    public records_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.lv_items,viewGroup,false);
        return new records_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull records_view_holder holder, int i) {
        if (!mcursor.moveToPosition(i))
        return;

        String time1 = mcursor.getString(mcursor.getColumnIndex(Table.column.column_lap1));
        String time2 = mcursor.getString(mcursor.getColumnIndex(Table.column.column_lap2));
        String time3 = mcursor.getString(mcursor.getColumnIndex(Table.column.column_lap3));
        String time4 = mcursor.getString(mcursor.getColumnIndex(Table.column.column_lap4));
        long id = mcursor.getLong(mcursor.getColumnIndex(Table.column._ID));
        holder.t1.setText(time1);
        holder.t2.setText(time2);
        holder.t3.setText(time3);
        holder.t4.setText(time4);
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public void swap_cursor(Cursor newcursor){
        if(mcursor != null){
            mcursor.close();
        }
        mcursor = newcursor;

        if(newcursor != null){
            notifyDataSetChanged();
        }
    }
}
