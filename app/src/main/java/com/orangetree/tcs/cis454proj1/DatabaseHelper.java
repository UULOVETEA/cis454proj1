package com.orangetree.tcs.cis454proj1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.orangetree.tcs.cis454proj1.Database;
import com.orangetree.tcs.cis454proj1.R;

/**
 * Created by Qiwu Zou on 2018/2/17.
 */

public class DatabaseHelper {
    private SQLiteDatabase DB_forWrite;
    private SQLiteDatabase DB_forRead;
    private Database db;
    DatabaseHelper (Context context){
        Database database = Database.getInstance(context.getApplicationContext());
        db = database;
        SQLiteDatabase sqliteDB_W = db.getWritableDatabase();
        SQLiteDatabase sqliteDB_R = db.getReadableDatabase();
        DB_forRead = sqliteDB_R;
        DB_forWrite = sqliteDB_W;
    }

    public boolean insertAccountAndPassword (String ID, String Password){
        if (!databaseContains(ID)) {
            ContentValues inputContent;
            inputContent = db.insertStringInfo("ACCOUNTNAME", ID);
            inputContent.putAll(db.insertStringInfo("PASSWORD", Password));
            DB_forWrite.insert("ACCOUNT", null, inputContent);
            return true;
        }
        else{
            return false;
        }
    }

    public String getPassword(String ID){
        Cursor cursor = DB_forRead.query("ACCOUNT", new String[] {"ACCOUNTNAME, PASSWORD"}, "ACCOUNTNAME = ?", new String[] {ID},null, null, null);

        cursor.moveToNext();
        return cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));




    }

    public boolean databaseContains(String ID){
        if (ID == null){
            return false;
        }
        Cursor cursor = DB_forRead.query("ACCOUNT", new String[] {"ACCOUNTNAME"}, "ACCOUNTNAME = ?", new String[] {ID},null, null, null);
        if (cursor.getCount() == 0){
            return false;
        }
        else
            return true;
    }
}
