package com.example.finsysproject;

public class StudentModel {
    String roll_no = "";
    String student_name = "";
    String student_class = "";

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_class() {
        return student_class;
    }

    public StudentModel(String roll_no, String student_name, String student_class) {
        this.roll_no = roll_no;
        this.student_name = student_name;
        this.student_class = student_class;
    }

    public StudentModel() {
    }


}
