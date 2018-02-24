package com.orangetree.tcs.cis454proj1;

/**
 * Created by Qiwu Zou on 2018/2/7.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String name = "db"; // database name
    private static final int version = 6; // database verison
    private Database(Context context) {

        super(context, name, null, version);
    }
    private static Database instance;

    public static synchronized Database getInstance(Context context){
        if (instance == null){
            instance = new Database(context.getApplicationContext());
        }
        return instance;
    }


    public ContentValues insertIntInfo(String columnName, int value){
        ContentValues values = new ContentValues();
        values.put(columnName, value);
        return values;
    }
    public ContentValues insertStringInfo(String columnName, String value){
        ContentValues values = new ContentValues();
        values.put(columnName, value);
        return values;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String execute2 = "CREATE TABLE ACCOUNT (ACCOUNTNAME TEXT KEY NOT NULL UNIQUE, PASSWORD TEXT, " +
                "PHONE TEXT, EMAIL TEXT);";
        db.execSQL(execute2);
        String execute = "CREATE TABLE APPOINTMENT ("

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}


