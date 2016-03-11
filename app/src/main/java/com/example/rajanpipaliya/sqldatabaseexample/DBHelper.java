package com.example.rajanpipaliya.sqldatabaseexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rajan pipaliya on 7/23/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSON=4;
    public static final String DATABASE_NAME="crub.db";
    public DBHelper(Context contex){
        super(contex,DATABASE_NAME,null,DATABASE_VERSON);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + studentdata.TABLE + "("
                + studentdata.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + studentdata.KEY_name + " TEXT, "
                + studentdata.KEY_age + " INTEGER, "
                + studentdata.KEY_email + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DRPO TABLE IF EXISTS"+studentdata.TABLE);
        onCreate(db);

        }
    }
