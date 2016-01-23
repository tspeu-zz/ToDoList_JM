package com.b_pachango.jm.todolist_bd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//
public class MainActivity extends  AppCompatActivity {

    public static final int NEW_ITEM = 1;
    public static final int EDIT_ITEM = 2;
    public static final int SHOW_ITEM = 3;
    //elemto seleccionado
    private int mLastRowSelected = 0;
    public static DataBaseHelper mDbHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //@Override
    public boolean OnCreateOptionsMenu(Menu menu){
        MenuInflater infla = getMenuInflater();
        infla.inflate(R.menu.menu, menu);
        return true;
    }

    //@Override
    public boolean OnOptionsItemSelected(MenuItem mItem){
        //
        switch(mItem.getItemId()){

            case R.id.new_item :
                Intent intent = new Intent(this, ItemActivity.class);
                startActivityForResult(intent, NEW_ITEM);
                return  true;
            default:
                return  super.onOptionsItemSelected(mItem);
        }

    }
}
