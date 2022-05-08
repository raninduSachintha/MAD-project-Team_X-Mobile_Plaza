package com.example.mobileplaza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;             //handel all crud functions
import android.database.sqlite.SQLiteOpenHelper;      //handle database quries
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {   //handel all crud functions

    public static  final String DATABASE_NAME = "CardInfo.db";         //set database for name

    public DBHelper(Context context) {super(context, DATABASE_NAME, null, 1);}     //database version,can backup by version

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.TABLE_NAME + " (" +
                        UsersMaster.Users._ID+ " INTEGER PRIMARY KEY," +
                        UsersMaster.Users.col_1+ " TEXT,"+
                        UsersMaster.Users.col_2+ " INTEGER,"+           //qery for creating the table
                        UsersMaster.Users.col_3+ " INTEGER,"+
                        UsersMaster.Users.col_4+ " INTEGER)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }
                                               //insert card
    public Long addCard(String cname, String cnum, String exp, String cacvv){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UsersMaster.Users.col_1, cname);
        values.put(UsersMaster.Users.col_2, Integer.parseInt(cnum));            //binding values in to containValue object
        values.put(UsersMaster.Users.col_3, Integer.parseInt(exp));             //Integer values need to represent like this,text normal way
        values.put(UsersMaster.Users.col_4, Integer.parseInt(cacvv));

        return db.insert(UsersMaster.Users.TABLE_NAME, null, values);     //pasing values to insert onbild method in Sqlitedatabase package for excecuti values

    }

    //read function
    public List readAll(){   //make fuction
      SQLiteDatabase db = getReadableDatabase();

      String [] projection = {
              UsersMaster.Users._ID,
              UsersMaster.Users.col_1,   //do projection
              UsersMaster.Users.col_2,
              UsersMaster.Users.col_3,
              UsersMaster.Users.col_4
      };
      String sortOrder = UsersMaster.Users._ID + " DESC";    //SHOW DATA AS DESCENDING ODER,ITS GETS BY _ID

        Cursor cursor = db.query(    //Cursor to retrive data from mydb
               UsersMaster.Users.TABLE_NAME,
               projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        //creating list of informatioin
        List info = new ArrayList<>();

        while (cursor.moveToNext()){
            String Card_Name = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.col_1));   //taking strings from tables
            String Card_Number = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.col_2));    //DATA GETS ONE BY ONE
            String Month_Year = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.col_3));
            String Cvv = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.col_4));

            info.add(Card_Name+":"+Card_Number+":"+Month_Year+":"+Cvv);    //SAVE THEM IN A LIST
        }
        cursor.close();

        return info;
    }

                                          //Delete in database
    public void deleteInfo(String Card_Name){         //ANY COLOUM CAN BE CHOOSE
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.Users.col_1 + " LIKE ?";               //check values is where,this the selection
        String[] stringArgs = {Card_Name};        //pass your coloum here

        db.delete(UsersMaster.Users.TABLE_NAME,selection,stringArgs);   //after indentify coloum,can delete,delete inbuld function from sqlitedatabase
    }


                                               //update data in database

    public void updateInfo(View view, String cname, String cnum, String exp, String cacvv){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();     //create object like raw

        values.put(UsersMaster.Users.col_1, cname);
        values.put(UsersMaster.Users.col_2, cnum);                   //updating rows
        values.put(UsersMaster.Users.col_3, exp);
        values.put(UsersMaster.Users.col_4, cacvv);

        String selection = UsersMaster.Users.col_1 + " LIKE ?";

        String[] selectionArgs = {cname} ;

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs

        );

        Snackbar snackbar = Snackbar.make(view, count+" Rows were affected!",Snackbar.LENGTH_LONG);    //like tost msg
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        snackbar.show();

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
