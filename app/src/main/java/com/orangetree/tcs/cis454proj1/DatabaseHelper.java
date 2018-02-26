package com.orangetree.tcs.cis454proj1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.orangetree.tcs.cis454proj1.Database;
import com.orangetree.tcs.cis454proj1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public int insertAppointment (int session, int offset){
            ContentValues inputContent;
            inputContent = db.insertIntInfo("AVAILABILITY", 1);
            inputContent.putAll(db.insertIntInfo("SESSION", session));
            inputContent.putAll(db.insertIntInfo("OFFSET", offset));
            DB_forWrite.insert("APPOINTMENT", null, inputContent);
            Cursor cursor = DB_forRead.rawQuery("SELECT last_insert_rowid();",new String[]{});
            cursor.moveToNext();
            int rowid = cursor.getInt(cursor.getColumnIndexOrThrow("last_insert_rowid()"));
            return rowid;

    }

    public int getAppNum(){
        Cursor cursor = DB_forRead.rawQuery("SELECT * FROM APPOINTMENT;", new String[]{});
        int temp = cursor.getCount();
        return temp;
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

    public int updateAppintment(int session, int offset, int availability) {
        ContentValues inputContent;
        inputContent = db.insertIntInfo("AVAILABILITY", availability);
        DB_forWrite.execSQL("UPDATE APPOINTMENT SET AVAILABILITY = " + availability + " WHERE SESSION = " + session + " AND OFFSET = " + offset + ";");
        Cursor cursor = DB_forRead.rawQuery("SELECT rowid, SESSION, OFFSET FROM APPOINTMENT WHERE SESSION = " + session + " AND OFFSET = " + offset + ";", new String[]{});
        cursor.moveToNext();
        int rowid = cursor.getInt(cursor.getColumnIndexOrThrow("rowid"));
        return rowid;
    }

    public void bindAccountWithApp(String name, int rowid){
        Cursor cursor = DB_forRead.query("ACCOUNTAPP", new String[]{"ACCOUNTNAME", "ID"}, "ACCOUNTNAME = ?", new String[]{name},null, null, null);
        if (cursor.getCount() == 0){
            ContentValues inputContent;
            inputContent = db.insertStringInfo("ACCOUNTNAME", name);
            inputContent.putAll(db.insertIntInfo("ID", rowid));
            DB_forWrite.insert("ACCOUNTAPP", null,inputContent);
        }
        else {
            DB_forWrite.execSQL("UPDATE ACCOUNTAPP SET ID = " + rowid + " WHERE ACCOUNTNAME = '" + name + "';");
        }
    }

    public String getAppWithAccount(String name){
        Cursor cursor = DB_forRead.rawQuery("SELECT * FROM ACCOUNTAPP WHERE ACCOUNTNAME = '" + name +"';", new String[]{});
        if (cursor.getCount() == 0){
            return "";
        }
        else {
            int session, offset;
            cursor.moveToNext();
            int temp = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            cursor = DB_forRead.rawQuery("SELECT * FROM APPOINTMENT WHERE rowid = " + temp + ";", new String[]{});
            cursor.moveToNext();
            session = cursor.getInt(cursor.getColumnIndexOrThrow("SESSION"));
            offset = cursor.getInt(cursor.getColumnIndexOrThrow("OFFSET"));
            Calendar Calendar_temp = Calendar.getInstance();
            Calendar_temp.add(Calendar.DATE, +session);
            Date date_temp = Calendar_temp.getTime();
            String dateTimeString = new SimpleDateFormat("MM/dd").format(date_temp);
            if (offset == 0){
                dateTimeString = dateTimeString + "   9:00 AM";
            }
            else {
                dateTimeString = dateTimeString + "   2:00 PM";
            }
            return dateTimeString;
        }

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

    public List<String> getAllApp(){
        List<String> temp = new ArrayList<>();
        int session;
        int offset;
        Cursor cursor = DB_forRead.rawQuery("SELECT * FROM APPOINTMENT", new String[]{});
        while (cursor.moveToNext()){
            if(cursor.getInt(cursor.getColumnIndexOrThrow("AVAILABILITY")) == 1){
            session = cursor.getInt(cursor.getColumnIndexOrThrow("SESSION"));
            offset = cursor.getInt(cursor.getColumnIndexOrThrow("OFFSET"));
            Calendar Calendar_temp = Calendar.getInstance();
            Calendar_temp.add(Calendar.DATE, +session);
            Date date_temp = Calendar_temp.getTime();
            String dateTimeString = new SimpleDateFormat("MM/dd").format(date_temp);
            if (offset == 0){
                dateTimeString = dateTimeString + "   9:00 AM";
            }
            else {
                dateTimeString = dateTimeString + "   2:00 PM";
            }
            temp.add(dateTimeString);
            }
            else{
                session = cursor.getInt(cursor.getColumnIndexOrThrow("SESSION"));
                offset = cursor.getInt(cursor.getColumnIndexOrThrow("OFFSET"));
                Calendar Calendar_temp = Calendar.getInstance();
                Calendar_temp.add(Calendar.DATE, +session);
                Date date_temp = Calendar_temp.getTime();
                String dateTimeString = new SimpleDateFormat("MM/dd").format(date_temp);
                if (offset == 0){
                    dateTimeString = dateTimeString + "   9:00 AM (unavailable)";
                }
                else {
                    dateTimeString = dateTimeString + "   2:00 PM (unavailable)";
                }
                temp.add(dateTimeString);

            }
        }
        return temp;
    }


    public String getEmail(String ID){

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
