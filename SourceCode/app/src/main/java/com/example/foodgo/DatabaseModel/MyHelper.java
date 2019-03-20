package com.example.foodgo.DatabaseModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodgo.Entity.User;

public class MyHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String USER_TABLE = "user_table";
    public static final String USER_TABLE1 = "address_table";


    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT , LASTNAME TEXT, ADDRESS TEXT, PHONENUMBER TEXT, USERNAME TEXT, PASSWORD TEXT,FOREIGN KEY(FIRSTNAME)REFERENCES address_table(FIRSTNAME))");
//        db.execSQL("create table " + USER_TABLE1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT PRIMARY KEY, ADDRESS TEXT )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public boolean insertData(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME", user.getFirstname());
        contentValues.put("LASTNAME", user.getLastname());
        contentValues.put("ADDRESS", user.getAddress());
        contentValues.put("PHONENUMBER", user.getPhonenumber());
        contentValues.put("USERNAME", user.getUsername());
        contentValues.put("PASSWORD", user.getPassword());
        long result = db.insert(USER_TABLE, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkAccountLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, new String[]{"ID"}, "USERNAME = ? AND PASSWORD = ?", new String[]{username, password}, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkExistAccount(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, new String[]{"ID"}, "USERNAME = ?", new String[]{username}, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
