package com.b_pachango.jm.todolist_bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JM_B on 22-Jan-16.
 */
public class DataBaseHelper {

    private Context mCtx= null;
    private DataBaseHelperInternal mDbHelper=null;
    private SQLiteDatabase mDb = null;
    private static final String DATABASE_NAME= "TODOLIST_JM";
    private static final int DATABASE_VERSION= 1;
    //tabla y campos
    private static final String DATABASE_TABLE_TODOLIST_JM= "todolistJM";
    public static final String SL_ID = "_id";
    public static final String SL_ITEM ="task";
    public static final String SL_PLACE = "place";
    public static final String SL_IMPORTANCE = "importance";
    public static final String SL_DESCRIPTION = "description" ;

    //sentencia de creacion de tabla
    private static final String DATABASE_CREATE_TODOLISTJM ="create table "+
            DATABASE_TABLE_TODOLIST_JM + "( "+ SL_ID +"integer primary key UNIQUE"+
            SL_ITEM + " text not null " + SL_PLACE +" text not null " +
            SL_IMPORTANCE +" integer not null, " + SL_DESCRIPTION + " text )";

/*CREATE  TABLE "main"."TODOLIST_JM" ("SL_ID" INTEGER PRIMARY KEY  NOT NULL  UNIQUE ,
"SL_ITEM" TEXT NOT NULL , "SL_PLACE" TEXT NOT NULL , "SL_IMPORTANCE" INTEGER NOT NULL ,
"SL_DESCRIPTION" TEXT)*/
    //constructor
    public DataBaseHelper(Context ctx){
        this.mCtx= ctx;

    }

    //para iniciar el uso de la base de datos
    public DataBaseHelper  open(){
        mDbHelper = new DataBaseHelperInternal(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
//cerrar
    public void close(){
        mDb.close();

    }

  /*ejecutar consultas  */

    public Cursor getAllItems(){
       return mDb.query(DATABASE_TABLE_TODOLIST_JM,
               new String[] { SL_ID, SL_ITEM,SL_PLACE, SL_IMPORTANCE, SL_DESCRIPTION },
               null,null,null,null,SL_IMPORTANCE);
    }

    public long insertItem(String item, String place, String descr, int impor){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SL_IMPORTANCE,impor);
        initialValues.put(SL_ITEM,item);
        initialValues.put(SL_PLACE,place);
        initialValues.put(SL_DESCRIPTION,descr);
        return mDb.insert(DATABASE_TABLE_TODOLIST_JM, null, initialValues);


    }

    //clase interna que executa la dB
    private static class DataBaseHelperInternal extends SQLiteOpenHelper {

        //constructor
        public DataBaseHelperInternal(Context ctx){

            super( ctx,DATABASE_NAME,null,DATABASE_VERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                createTables(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                deleteTables(db);
                createTables(db);
        }


        private void createTables (SQLiteDatabase db){

            db.execSQL(DATABASE_CREATE_TODOLISTJM);
        }

        private void deleteTables(SQLiteDatabase db){

            db.execSQL("drop table if exists " + DATABASE_CREATE_TODOLISTJM);
        }

    }


}
