package com.louismarzoratiHCCP.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Louis on 3/6/14.
 */
public class CheckOut extends ActionBarActivity   {

    private ListView lv;
    private ArrayList<Student> list1;
    private EditText etPassNumber;
    private static ArrayList<Integer> passes = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out_layout);

        list1 = SimpleDatabase.getArrayList();
        load();

        etPassNumber = (EditText) findViewById(R.id.editPassToCheckIn);


        lv = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this,
        android.R.layout.simple_list_item_1,list1);
        lv.setAdapter(arrayAdapter);

        arrayAdapter.notifyDataSetChanged();

        Button back = (Button) findViewById(R.id.buttonBack2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        Button load = (Button) findViewById(R.id.buttonLoadThis);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        Button sumbit1 = (Button) findViewById(R.id.buttonCheckIn);
        sumbit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIn();
                arrayAdapter.notifyDataSetChanged();
                etPassNumber.setText("");
            }
        });

    }

    public void checkIn(){
        int idx= 0;
        String passEntered = etPassNumber.getText().toString();

        while(idx < list1.size()){
            if(list1.get(idx).getPass().equals(passEntered)){
                Toast.makeText(this, String.format("Pass # "+list1.get(idx).getPass()
                        +" was removed"), Toast.LENGTH_SHORT).show();
                list1.remove(idx);
            } else {
                idx++;
            }
        }


    }

    public void save(){
        String ser = SerializeObject.objectToString(list1);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializeObject.WriteSettings(CheckOut.this, ser, "myobject.dat");
        } else {
            SerializeObject.WriteSettings(CheckOut.this, "", "myobject.dat");
        }
    }

    public void load(){
        String ser = SerializeObject.ReadSettings(CheckOut.this, "myobject.dat");
        if (ser != null && !ser.equalsIgnoreCase("")) {
            Object obj = SerializeObject.stringToObject(ser);
            // Then cast it to your object and
            if (obj instanceof ArrayList) {
                // Do something
                list1 = (ArrayList<Student>)obj;
            }
        }
    }
    public static ArrayList<Integer> getPasses(){
        return passes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}


