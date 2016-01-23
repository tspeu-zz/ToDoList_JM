package com.b_pachango.jm.todolist_bd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JM_B on 22-Jan-16.
 */
public class ItemActivity  extends Activity {

    TextView mItem, mPlace,  mDescription, mImportance = null;

    Integer mRowId = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_item);

        mImportance = (TextView)findViewById(R.id.importance);
        mItem =(TextView) findViewById(R.id.item);
        mPlace = (TextView) findViewById(R.id.place);
        mDescription=(TextView) findViewById(R.id.description);

        Button btnSave = (Button)findViewById(R.id.add);
        btnSave.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  setResult(RESULT_OK);
                  saveData();
                  finish();

               }
           });
    }

    public void saveData()  {
    //obtener datos de los campos
        String itemTxt = mItem.getText().toString().toLowerCase();
        String palceTxt = mPlace.getText().toString().toLowerCase();
        String descripTxt =mDescription.getText().toString();
        String importanTxt = mImportance.getText().toString();
    //insertar en la bd
        try {
            MainActivity.mDbHelper.open();
            MainActivity.mDbHelper.insertItem(itemTxt, palceTxt, descripTxt, Integer.parseInt(importanTxt));
            MainActivity.mDbHelper.close();
            
        } catch (Exception e){
            e.printStackTrace();

        }

        // showMenssage(R.string.dataerror);

    }




}



