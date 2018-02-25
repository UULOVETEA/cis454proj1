package com.orangetree.tcs.cis454proj1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.orangetree.tcs.cis454proj1.Database;
import com.orangetree.tcs.cis454proj1.R;

import static android.os.Build.ID;

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
    public boolean updatePhoneAndEmail (String ID, String phone, String email){
        if (databaseContains(ID)) {
            ContentValues inputContent;
            inputContent = db.insertStringInfo("PHONE", phone);
            inputContent.putAll(db.insertStringInfo("EMAIL", email));
            DB_forWrite.update("ACCOUNT", inputContent,"ACCOUNTNAME = ?", new String[]{ID});
            return true;
        }
        else{
            return false;
        }
    }

    public int insertAppointment (){
            ContentValues inputContent;
            inputContent = db.insertIntInfo("AVAILABILITY", 1);
            DB_forWrite.insert("APPOINTMENT", null, inputContent);
            Cursor cursor = DB_forRead.rawQuery("SELECT last_insert_rowid();",new String[]{});
            cursor.moveToNext();
            int rowid = cursor.getInt(cursor.getColumnIndexOrThrow("last_insert_rowid()"));
            return rowid;

    }

    public boolean updatePassword(String ID, String password){
        if (databaseContains(ID)) {
            ContentValues inputContent;
            inputContent = db.insertStringInfo("PASSWORD", password);
            DB_forWrite.update("ACCOUNT", inputContent,"ACCOUNTNAME = ?", new String[]{ID});
            return true;
        }
        else{
            return false;
        }
    }

    public void updateAppintment(int ID, int availability){
        ContentValues inputContent;
        inputContent = db.insertIntInfo("AVAILABILITY", availability);
        DB_forWrite.execSQL("UPDATE APPOINTMENT SET AVAILABILITY = " + availability + " WHERE rowid = " + ID + ";");
    }

    public String getPhone(String ID){

        String phone = "ACCOUNT DOES NOT EXIST";

        if (ID != null && databaseContains(ID)) {
            Cursor cursor = DB_forRead.query("ACCOUNT", new String[] {"ACCOUNTNAME, PHONE"}, "ACCOUNTNAME = ?", new String[] {ID},null, null, null);
            int counter = cursor.getCount();
            cursor.moveToNext();
            phone = cursor.getString(cursor.getColumnIndexOrThrow("PHONE"));
        }

        return phone;
    }

    public String geteEmail(String ID){

        String email = "ACCOUNT DOES NOT EXIST";

        if (ID != null && databaseContains(ID)) {
            Cursor cursor = DB_forRead.query("ACCOUNT", new String[] {"ACCOUNTNAME, EMAIL"}, "ACCOUNTNAME = ?", new String[] {ID},null, null, null);
            int counter = cursor.getCount();
            cursor.moveToNext();
            email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL"));
        }

        return email;
    }

    public String getPassword(String ID){

        String password = "ACCOUNT DOES NOT EXIST";

        if (ID != null && databaseContains(ID)) {
            Cursor cursor = DB_forRead.query("ACCOUNT", new String[] {"ACCOUNTNAME, PASSWORD"}, "ACCOUNTNAME = ?", new String[] {ID},null, null, null);

            cursor.moveToNext();
            password = cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"));
        }

        return password;
    }

    public int getAvailability(String ID){
        Cursor cursor = DB_forRead.query("APPOINTMENT", new String[] {"rowid, AVAILABILITY"}, "rowid = ?", new String[] {ID},null, null, null);
        cursor.moveToNext();
        int temp = cursor.getInt(cursor.getColumnIndexOrThrow("AVAILABILITY"));
        return temp;
    }
    public boolean databaseContains(String ID) {

        Boolean flag = true;

        if (ID != null) {
            Cursor cursor = DB_forRead.query("ACCOUNT", new String[]{"ACCOUNTNAME"}, "ACCOUNTNAME = ?", new String[]{ID}, null, null, null);
            if (cursor.getCount() == 0) {
                flag = false;
            }

        }
        return flag;
    }
}
