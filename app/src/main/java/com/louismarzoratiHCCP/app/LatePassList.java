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
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Louis on 3/10/14.
 */
public class LatePassList extends ActionBarActivity {

    private static ArrayList<String> lateList = new ArrayList<String>();
    private ArrayList<Student> list1;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.late_pass_list);

        list1 = SimpleDatabase.getArrayList();

        final Calendar c = Calendar.getInstance();


        lv = (ListView) findViewById(R.id.listViewLate);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,lateList);
        lv.setAdapter(arrayAdapter);

        Button late = (Button) findViewById(R.id.buttonLatePasses);
        late.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lateList.clear();
                isLate();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        Button back = (Button) findViewById(R.id.buttonBackLate);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void isLate(){
        int idx =0;
        Calendar c = Calendar.getInstance();
    while(idx < list1.size()) {

        if (list1.get(idx).getPassType().equals("Overnight Pass")){
            Calendar s = list1.get(idx).getDate();
            s.add(Calendar.DATE,1);
            if(c.after(s)){
                lateList.add(list1.get(idx).getEmail());
                    s.add(Calendar.DATE,-1);
            }
        }

        if(list1.get(idx).getPassType().equals("Weekend Pass")){
            Calendar w = list1.get(idx).getDate();
            w.add(Calendar.DATE,3);
            if(c.after(w)){
                lateList.add(list1.get(idx).getEmail());
                w.add(Calendar.DATE,-3);
            }
        }
        idx++;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
