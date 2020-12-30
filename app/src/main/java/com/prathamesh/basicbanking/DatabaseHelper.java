package com.prathamesh.basicbanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9876543210,'Aniket',1000.00,'user101@gmail.com','18xxxxxx01','BKID333')");
        db.execSQL("insert into user_table values(9878562135,'Prathamesh',1000.00,'user102@gmail.com','18xxxxxx02','BKID334')");
        db.execSQL("insert into user_table values(8565457825,'Harshad',10000.00,'user103@gmail.com','18xxxxxx03','BKID335')");
        db.execSQL("insert into user_table values(9899696472,'Virat',1000.00,'user104@gmail.com','18xxxxxx04','BKID336')");
        db.execSQL("insert into user_table values(9987533221,'Aditya',1000.00,'user105@gmail.com','18xxxxxx05','BKID337')");
        db.execSQL("insert into user_table values(9145789863,'Ben',1000.00,'user106@gmail.com','18xxxxxx06','BKID338')");
        db.execSQL("insert into user_table values(6988552247,'Rohan',1000.00,'user107@gmail.com','18xxxxxx07','BKID339')");
        db.execSQL("insert into user_table values(9882545999,'Tushar',1000.00,'user108@gmail.com','18xxxxxx08','BKID343')");
        db.execSQL("insert into user_table values(9359797929,'Rohit',1000.00,'user109@gmail.com','18xxxxxx09','BKID341')");
        db.execSQL("insert into user_table values(9882545789,'Shivam',1000.00,'user110@gmail.com','18xxxxxx10','BKID342')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
