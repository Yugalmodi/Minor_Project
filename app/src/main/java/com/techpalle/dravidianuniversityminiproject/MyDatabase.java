package com.techpalle.dravidianuniversityminiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 03-Jan-17.
 */
public class MyDatabase {
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    public MyDatabase(Context c){
        myHelper = new MyHelper(c, "dravindian.db", null, 1);
    }
    public void open(){
        sqLiteDatabase = myHelper.getWritableDatabase();
    }
    public void insertStudent(String no, String name, String mobile, String email, String subject, String desc, String date){
        ContentValues cv = new ContentValues();
        cv.put("no", no);
        cv.put("name", name);
        cv.put("mobile", mobile);
        cv.put("email", email);
        cv.put("subject", subject);
        cv.put("desc",desc);
        cv.put("date", date);
        sqLiteDatabase.insert("student", null, cv);
    }
    public Cursor queryStudent(){
        Cursor c= null;
        c = sqLiteDatabase.query("student", null, null, null,null, null,null);
        return c;
    }
    public Cursor queryEmail(String email){
        Cursor c= null;
        c = sqLiteDatabase.query("student", null, "email =?", new String[]{email},null, null,null);
        return c;
    }
    public Cursor queryName(String name){
        Cursor c= null;
        c = sqLiteDatabase.query("student", null, "name =?", new String[]{name},null, null,null);
        return c;
    }
    public Cursor queryMobile(String mobile){
        Cursor cursor = null;
        cursor = sqLiteDatabase.query("student", null, "mobile =?", new String[]{mobile}, null, null, null);
        return cursor;
    }
    public void close(){
        sqLiteDatabase.close();
    }
    private class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table student(_id integer primary key, no text, name text, " +
                    "mobile text, email text, subject text, desc text , date text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
