package com.example.rajanpipaliya.sqldatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rajan pipaliya on 7/23/2015.
 */
public class StudentRepo
{

    private DBHelper dbhelper;
    public StudentRepo(Context context){
        dbhelper=new DBHelper(context);
    }
    public int insert(studentdata student){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(studentdata.KEY_name,student.name);
        values.put(studentdata.KEY_age,student.age);
        values.put(studentdata.KEY_email,student.age);

        long student_id=db.insert(studentdata.TABLE,null,values);
        return (int)student_id;
    }
    public void delete(int student_id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        db.delete(studentdata.TABLE,studentdata.KEY_ID+"=?",new String[]{String.valueOf(student_id)});
        db.close();
    }
    public void update(studentdata student){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(studentdata.KEY_name,student.name);
        values.put(studentdata.KEY_age,student.age);
        values.put(studentdata.KEY_email,student.age);
        db.update(studentdata.TABLE,values,studentdata.KEY_ID+"=?",new String[]{String.valueOf(student.student_ID)});
        db.close();
    }

    public ArrayList<HashMap<String,String>>getStudentList(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                studentdata.KEY_ID + "," +
                studentdata.KEY_name + "," +
                studentdata.KEY_email + "," +
                studentdata.KEY_age +
                " FROM " + studentdata.TABLE;
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> student=new HashMap<String, String>();
                student.put("id",cursor.getString(cursor.getColumnIndex(studentdata.KEY_ID)));
                student.put("name",cursor.getString(cursor.getColumnIndex(studentdata.KEY_name)));
                studentList.add(student);
            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return studentList;
    }
    public studentdata getstudentById(int id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                studentdata.KEY_ID + "," +
                studentdata.KEY_name + "," +
                studentdata.KEY_email + "," +
                studentdata.KEY_age +
                " FROM " + studentdata.TABLE
                + " WHERE " +
                studentdata.KEY_ID + "=?";

        int iCount =0;
        studentdata student = new studentdata();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id) } );
        if (cursor.moveToFirst()) {
            do {
                student.student_ID =cursor.getInt(cursor.getColumnIndex(studentdata.KEY_ID));
                student.name =cursor.getString(cursor.getColumnIndex(studentdata.KEY_name));
                student.email  =cursor.getString(cursor.getColumnIndex(studentdata.KEY_email));
                student.age =cursor.getInt(cursor.getColumnIndex(studentdata.KEY_age));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return student;
    }
}
