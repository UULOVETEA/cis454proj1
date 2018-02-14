package com.orangetree.tcs.cis454proj1;

/**
 * Created by Qiwu Zou on 2018/2/7.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String name = "db"; // database name
    private static final int version = 1; // database verison
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

    public void createTable(SQLiteDatabase db){
        String execute = "CREATE TABLE INFO(ID INT PRIMARY KEY NOT NULL, NAME TEXT);";
        db.execSQL(execute);
    }

    public void insertInfo(SQLiteDatabase db, int ID, String name){
        String execute = "INSERT INTO INFO (ID, NAME)" +
                "VALUES (" + ID + ", " + name + " );";
        db.execSQL(execute);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}


