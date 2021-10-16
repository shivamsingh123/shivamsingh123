package com.example.finsysproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class SqliteDatabaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME= "Finsys";
    private static final String TABLE_NAME= "student_record";
    private static final int VERSION= 1;

    public SqliteDatabaseHandler(Context context){
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, roll_no INTEGER, sname varchar(250), sclass INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME+"";
        sqLiteDatabase.execSQL(query);
    }

    public long insert_data(StudentModel studentModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("roll_no", studentModel.getRoll_no());
        values.put("sname", studentModel.getStudent_name());
        values.put("sclass", studentModel.getStudent_class());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long update_data(StudentModel studentModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("roll_no", studentModel.getRoll_no());
        long id = db.update(TABLE_NAME, values, "roll_no="+ studentModel.roll_no, null);
        db.close();
        return id;
    }

    public long delete_data(StudentModel studentModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("roll_no", studentModel.getRoll_no());
        long id = db.delete(TABLE_NAME, "roll_no="+ studentModel.roll_no, null);
        db.close();
        return id;
    }

    public long clear_data(StudentModel studentModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("roll_no", studentModel.getRoll_no());
        long id = db.delete(TABLE_NAME, null, null);
        db.close();
        return id;
    }

    public ArrayList<StudentModel> get_student_records() {
        ArrayList<StudentModel> List = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+"", null);

        if(cursor.moveToFirst()) {
            do {
                List.add(new StudentModel(

                        cursor.getString(cursor.getColumnIndex("roll_no")),
                        cursor.getString(cursor.getColumnIndex("sname")),
                        cursor.getString(cursor.getColumnIndex("sclass"))
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return List;
    }

}
