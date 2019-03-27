package com.example.foodgo.DatabaseModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodgo.Entity.User;
import com.example.foodgo.Entity.UserAddress;

public class MyHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String USER_TABLE = "user_table";
    public static final String USER_TABLE1 = "address_table";


    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT , LASTNAME TEXT, ADDRESS TEXT, PHONENUMBER TEXT, USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("create table " + USER_TABLE1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS TEXT , CITY TEXT,STATE TEXT, COUNTRY TEXT,POSTALCODE TEXT, KNOWNNAME TEXT,EMAIL TEXT )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE1);
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

    public void insertDataAddress(UserAddress userAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ADDRESS", userAddress.getAddress());
        contentValues.put("CITY", userAddress.getCity());
        contentValues.put("STATE", userAddress.getState());
        contentValues.put("COUNTRY", userAddress.getCountry());
        contentValues.put("POSTALCODE", userAddress.getPostalCode());
        contentValues.put("KNOWNNAME", userAddress.getKnownName());
        contentValues.put("EMAIL", userAddress.getEmail());
        db.insert(USER_TABLE1, null, contentValues);
        db.close();
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

    public User getDataUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT = "SELECT * FROM " + USER_TABLE + " WHERE USERNAME = '" + username +"'";
        Cursor cursor = db.rawQuery(SELECT, null);

        User user = new User();
        if (cursor.moveToLast())
        {
            user.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            user.setFirstname(cursor.getString(cursor.getColumnIndex("FIRSTNAME")));
            user.setLastname(cursor.getString(cursor.getColumnIndex("LASTNAME")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("ADDRESS")));
            user.setPhonenumber(cursor.getString(cursor.getColumnIndex("PHONENUMBER")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("USERNAME")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("PASSWORD")));
        }
        db.close();
        return user;
    }


    public void updatePassword(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE = "UPDATE " + USER_TABLE + "set PASSWORD = '" + password + "' where USERNAME ='" + email + "'";
        db.rawQuery(UPDATE, null);
        db.close();
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
