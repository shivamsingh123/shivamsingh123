package com.example.finsysproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.view_holder> {
    Context context;

    public myAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_layout, parent, false);
        return new view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        StudentModel student = MainActivity.StudentAllRecords.get(position);
        holder.roll_no.setText(student.getRoll_no());
        holder.sname.setText(student.student_name);
        holder.sclass.setText(student.student_class);
    }

    @Override
    public int getItemCount() {
        return MainActivity.StudentAllRecords.size();
    }

    public class view_holder extends RecyclerView.ViewHolder{
        TextView roll_no, sname, sclass;
        public view_holder(@NonNull View itemView) {
            super(itemView);

            roll_no = itemView.findViewById(R.id.grid_roll_no);
            sname = itemView.findViewById(R.id.grid_sname);
            sclass = itemView.findViewById(R.id.grid_sclass);
        }
    }
}
