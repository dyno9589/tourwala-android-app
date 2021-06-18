package com.botarmy.tourwala;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;


//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    public DatabaseHelper(Context context) {
//        super(context,"Login.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("Create table user(email text primary key,password text)");
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists user");
//
//    }
//
//    //inserting in database
//    public boolean insert(String email,String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//        contentValues.put("email",email);
//        contentValues.put("password",password);
//        long ins = db.insert("user",null,contentValues);
//        if(ins == -1) return false;
//        else return true;
//    }
//
//    //checking if mail exists;
//    public Boolean chkemail(String email){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
//        if(cursor.getCount()>0) return false;
//        else return true;
//    }
//
//    //checking the email and password;
//    public Boolean emailpassword(String email, String password){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});
//        if(cursor.getCount()>0) return true;
//        else return false;
//    }
//
//}

// Option 2nd
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    public DatabaseHelper(Context context) {
//        super(context, "Login.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("Create table user(email text primary key,password text)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists user");
//    }
//
//    //inserting in database
//    public boolean insert(String email, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("email", email);
//        contentValues.put("password", password);
//        long ins = db.insert("user", null, contentValues);
//        db.close();
//
//        if (ins == -1) return false;
//        else return true;
//    }
//
//    //checking if mail exists;
//    public Boolean chkemail(String email) {
//        boolean flag;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
//        if (cursor != null && cursor.getCount() > 0) flag = false;
//        else flag = true;
//
//        if (cursor != null && !cursor.isClosed())
//            cursor.close();
//
//        db.close();
//        return flag;
//    }
//
//    //checking the email and password;
//    public Boolean emailpassword(String email, String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
//        if (cursor.getCount() > 0) return true;
//        else return false;
//    }
//}

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text,email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    //inserting in database
    public boolean insert(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        db.close();

        if (ins == -1) return false;
        else return true;
    }

    //checking if mail exists;
    public Boolean chkemail(String email) {
        boolean flag;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor != null && cursor.getCount() > 0) flag = false;
        else flag = true;

        if (cursor != null && !cursor.isClosed())
            cursor.close();

        db.close();
        return flag;
    }

    //checking the email and password;
    public Boolean emailpassword(String email, String password) {
        boolean flag;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if (cursor != null && cursor.getCount() > 0) flag = true;
        else flag = false;

        if (cursor != null && !cursor.isClosed())
            cursor.close();

        db.close();
        return flag;
    }

//    public Boolean chkwelcome(String username) {
//        boolean flag;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select username from user where email=?", new String[]{username});
////        StringBuffer buffer = new StringBuffer();
//        if (cursor != null && cursor.getCount() > 0) flag = false;
//        else flag = true;
//
//        if (cursor != null && !cursor.isClosed())
//            cursor.close();
//
//        db.close();
////        while (cursor.moveToNext()) {
////            buffer.append("username" + cursor.getString(0));
//            return flag;
//
//        }

    //fetching username for homepage

    public String chkwelcome(String email) {
        String userName = "";

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from user where email=?", new String[]{email});
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst())
                    userName = cursor.getString(0);
            }

            if (cursor != null && !cursor.isClosed())
                cursor.close();

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

}



