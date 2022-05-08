package com.example.mobileplaza.database;

import android.provider.BaseColumns;         //tabele structre here

public final class UsersMaster {   ////write all the qureis inside this
    private  UsersMaster(){}  //CREATE CONSTRUCTOR

    public  static  class  Users implements BaseColumns {   //GETS BaseColumns MTHODS FOR CRATE TABLE,its create automaticaly id

        public static  final String TABLE_NAME = "card_details";
        public static  final String col_1 = "Card_Name";              //TABLE COLOUM CREATION
        public static  final String col_2 = "Card_Number";
        public static  final String col_3 = "Month_Year";
        public static  final String col_4 = "Cvv";
    }


}
