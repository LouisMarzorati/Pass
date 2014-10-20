package com.louismarzoratiHCCP.app;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
/**
 * Created by Louis on 2/25/14.
 */
public class SimpleDatabase extends ActionBarActivity {

    private Student stu;
    private EditText etName;
    private EditText etEmail;
    private TextView viewType;
    private static ArrayList<Student> list = new ArrayList<Student>();
    private final List<Integer> listItemPass = new ArrayList<Integer>();
    private final List<String> listItemUnit1 = new ArrayList<String>();
    private final List<String> listItemUnit2 = new ArrayList<String>();
    private final List<String> listPassType = new ArrayList<String>();
    private final List<String> listComplex = new ArrayList<String>();
    private final Calendar c = Calendar.getInstance();
    private String inputPassNumber;
    private String inputUnitNumber;
    private String inputComplex;
    private String inputPassType;
    private Calendar etDate;
    private boolean isChecked;
    private boolean isWeekend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pass_layout);

        load();

        final String[] hcu = getResources().getStringArray(R.array.HCU);
        final List<String> hcuConvert = Arrays.asList(hcu);

        final String [] cpu = getResources().getStringArray(R.array.CPU);
        final List<String> cpuConvert = Arrays.asList(cpu);


        for(int i = 1000;i<=1051;i++) {
            listItemPass.add(i);
        }


        for (int i = 0; i < hcuConvert.size(); i++) {
            listItemUnit1.add(hcuConvert.get(i));
        }

        for(int i = 0; i < cpuConvert.size(); i++) {
            listItemUnit2.add(cpuConvert.get(i));
        }

        listComplex.add("Hillcrest");
        listComplex.add("Country Place");

        listPassType.add("Overnight Pass");
        listPassType.add("Weekend Pass");

        TextView DisplayDate = (TextView) findViewById(R.id.textTodaysDate);
        final TextView DueDate = (TextView) findViewById(R.id.textDueDate);
        final TextView viewType = (TextView) findViewById(R.id.textType);

        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        DisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mm+1).append("/")
                .append(dd).append("/").append(yy));
        etDate = c;


        final Spinner sp1 = (Spinner) findViewById(R.id.spinnerPass);
        ArrayAdapter<Integer> adp1 = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_1,listItemPass);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp1);

        final Spinner sp2 = (Spinner) findViewById(R.id.spinnerUnit);

        final ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listItemUnit1);

        final ArrayAdapter<String> adp2b = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listItemUnit2);
        sp2.setAdapter(adp2);

        final Spinner sp3 = (Spinner) findViewById(R.id.spinnerPassType);
        ArrayAdapter<String> adp3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listPassType);
        adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adp3);

        final Spinner sp4 = (Spinner) findViewById(R.id.spinnerComplex);
        ArrayAdapter<String> adp4 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                listComplex);
        sp4.setAdapter(adp4);

        registerViews();

        Button back = (Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) {
                // TODO Auto-generated method stub
                String text = sp1.getSelectedItem().toString();
                inputPassNumber = text;

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) {
                String text = sp2.getSelectedItem().toString();
                inputUnitNumber = text;

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) {
                if(sp3.getSelectedItem().toString().equals("Overnight Pass")){
                    String text = sp3.getSelectedItem().toString();
                    inputPassType = text;

                    DueDate.setText("Due Tomorrow");
                    viewType.setText("\nYou are checking out a overnight parking " +
                            "pass, you will be charged $150 \nif it is not returned by " +
                            "tomorrow. Do you agree to these terms?");
                } else {
                    String text = sp3.getSelectedItem().toString();
                    inputPassType = text;
                    DueDate.setText("Due Monday");
                    viewType.setText("\nYou are checking out a weekend parking " +
                            "pass, you will be charged $150 if it is not returned by " +
                            "Monday. Do you agree to these terms?");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id) {
                String text = sp4.getSelectedItem().toString();
                inputComplex = text;
                if(sp4.getSelectedItem().toString().equals("Hillcrest")){

                    sp2.setAdapter(adp2);
                }else {

                    sp2.setAdapter(adp2b);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


    }


    private void registerViews(){

        etName = (EditText) findViewById(R.id.editName);




        etEmail = (EditText) findViewById(R.id.editEmail);


        Button btnSubmit = (Button) findViewById(R.id.buttonSumbit);

        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if ( checkValidation () && checkbox.isChecked() ){
                    submitForm();
                    createStudent();
                    save();
                    clearTextFields();
                    checkbox.setChecked(false);
                    showDialog(findViewById(R.layout.my_dialog));


                } else if (!Validation.hasText(etName)){
                    Toast.makeText(SimpleDatabase.this,"You must enter your name",
                            Toast.LENGTH_LONG).show();
                } else if (!Validation.isEmailAddress(etEmail, true)){
                    Toast.makeText(SimpleDatabase.this,"You must enter a valid email",
                            Toast.LENGTH_LONG).show();
                }
                  else if(!checkbox.isChecked()){
                    Toast.makeText(SimpleDatabase.this,"You must select Agree to Terms.",
                            Toast.LENGTH_LONG).show();
                } else if(!UsedPass()){
                    Toast.makeText(SimpleDatabase.this,"That pass is in use, select another.",
                            Toast.LENGTH_LONG).show();
                } else if (inputPassType.equals("Weekend Pass")
                        && isWeekend()!=true){
                    Toast.makeText(SimpleDatabase.this,"Can only check out weekend pass on friday",
                            Toast.LENGTH_LONG).show();
                }
                else if(hasPassOut()){
                    Toast.makeText(SimpleDatabase.this,"You must turn in previous pass" +
                                    " before you check out another",
                            Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(SimpleDatabase.this, "Errors in form...", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        // Submit your form here. your form is valid
        Toast.makeText(this, String.format("Parking Pass Added"), Toast.LENGTH_LONG).show();

    }


    //change Type of pass spinner to a text view.

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(etName)) ret = false;
        if (!Validation.isEmailAddress(etEmail, true)) ret = false;
        if (!UsedPass()) ret = false;
        if(hasPassOut()) ret = false;
        if (inputPassType.equals("Weekend Pass")
                && isWeekend()!=true) {
            ret = false;
        }
        return ret;
    }

    public void clearTextFields(){
        etEmail.setText("");
        etName.setText("");
    }

    public void createStudent(){

        stu = new Student(etName.getText().toString(),inputUnitNumber,
                etEmail.getText().toString(),inputPassNumber,etDate,inputComplex,inputPassType);
        list.add(stu);
    }

    public boolean UsedPass(){
        for(Student s : list)
            if(s.getPass().equals(inputPassNumber))
                return false;
        return true;
    }


    public static ArrayList<Student> getArrayList(){
        return list;
    }


    public boolean isWeekend(){
        Calendar e = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK,first.getFirstDayOfWeek() -
                first.get(Calendar.DAY_OF_WEEK));

        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR,5);

        int dayFri = last.get(Calendar.DAY_OF_WEEK);

        if(e.get(Calendar.DAY_OF_WEEK)==dayFri){
            return true;
        }
        return false;
    }

    public boolean hasPassOut(){
        int idx = 0;
        if(list.size()==0){
            return false;
        }
        else {
            while(idx<list.size()){
                if(list.get(idx).getName().toLowerCase().equals(etName.getText()
                        .toString().toLowerCase()) || (list.get(idx).getEmail().toLowerCase()
                        .equals(etEmail.getText().toString().toLowerCase() )) ){
                    return true;
                }
                idx++;
            }

        }
        return false;
    }

    public void save(){
        String ser = SerializeObject.objectToString(list);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializeObject.WriteSettings(SimpleDatabase.this, ser, "myobject.dat");
        } else {
            SerializeObject.WriteSettings(SimpleDatabase.this, "", "myobject.dat");
        }
    }

    public void load(){

        String ser = SerializeObject.ReadSettings(SimpleDatabase.this, "myobject.dat");
        if (ser != null && !ser.equalsIgnoreCase("")) {
            Object obj = SerializeObject.stringToObject(ser);
            // Then cast it to your object and
            if (obj instanceof ArrayList) {
                // Do something
                list = (ArrayList<Student>)obj;
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showDialog(View v){
        FragmentManager manager = getFragmentManager();
        myDialog md = new myDialog();
        md.show(manager,"MyDialog");
    }

}
