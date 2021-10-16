package com.example.finsysproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd, btnupdate, btndelete, btnclear;
    RecyclerView datalist;
    EditText txtsroll_no, txtsname, txtsclass;

    String roll_no ="", student_name="", student_class="";
    SqliteDatabaseHandler handler;
    myAdapter adapter;
    StudentModel studentModel = new StudentModel();
    public static ArrayList<StudentModel> StudentAllRecords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize_layout_components();
        handler = new SqliteDatabaseHandler(this);
        StudentAllRecords = handler.get_student_records();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        datalist.setLayoutManager(layoutManager);
        adapter = new myAdapter(this);
        datalist.setAdapter(adapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Add_btn_method();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_btn_method();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_btn_method();
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear_btn_method();
            }
        });

    }

    private void update_btn_method() {
        roll_no = txtsroll_no.getText().toString();
        student_name = txtsname.getText().toString();
        student_class = txtsclass.getText().toString();
        if(roll_no.isEmpty() || student_name.isEmpty() || student_class.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter Student Record!!", Toast.LENGTH_SHORT).show();
            return;
        }
        studentModel = new StudentModel();
        studentModel.setRoll_no(roll_no);
        studentModel.setStudent_name(student_name);
        studentModel.setStudent_class(student_class);

        handler.update_data(studentModel);
        StudentAllRecords = handler.get_student_records();
        adapter.notifyDataSetChanged();
    }

    private void clear_btn_method() {
        studentModel = new StudentModel();
        studentModel.setRoll_no(roll_no);
        handler.clear_data(studentModel);
        StudentAllRecords = handler.get_student_records();
        adapter.notifyDataSetChanged();
    }

    private void delete_btn_method() {
        roll_no = txtsroll_no.getText().toString();
        student_name = txtsname.getText().toString();
        student_class = txtsclass.getText().toString();
        if(roll_no.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter Roll Number!!", Toast.LENGTH_SHORT).show();
            return;
        }
        studentModel = new StudentModel();
        studentModel.setRoll_no(roll_no);
        handler.delete_data(studentModel);
        StudentAllRecords = handler.get_student_records();
        adapter.notifyDataSetChanged();
    }

    private void Add_btn_method(){

        roll_no = txtsroll_no.getText().toString();
        student_name = txtsname.getText().toString();
        student_class = txtsclass.getText().toString();
        if(roll_no.isEmpty() || student_name.isEmpty() || student_class.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter Student Record!!", Toast.LENGTH_SHORT).show();
            return;
        }
        studentModel = new StudentModel();
        studentModel.setRoll_no(roll_no);
        studentModel.setStudent_name(student_name);
        studentModel.setStudent_class(student_class);

        handler.insert_data(studentModel);
        StudentAllRecords = handler.get_student_records();
        adapter.notifyDataSetChanged();
    }

    private void initialize_layout_components() {
        btnadd = findViewById(R.id.btnadd);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        btnclear = findViewById(R.id.btnclear);
        txtsroll_no = findViewById(R.id.roll_no);
        txtsname = findViewById(R.id.sname);
        txtsclass = findViewById(R.id.sclass);
        datalist = findViewById(R.id.datagrid);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StudentAllRecords = handler.get_student_records();
        adapter.notifyDataSetChanged();

    }
}