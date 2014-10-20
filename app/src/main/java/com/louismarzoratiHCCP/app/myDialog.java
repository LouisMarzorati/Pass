package com.louismarzoratiHCCP.app;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Louis on 3/14/14.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class myDialog extends DialogFragment implements View.OnClickListener{
    Button cool;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_dialog,null);
       cool =(Button) view.findViewById(R.id.buttonCool);
        cool.setOnClickListener(this);
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonCool){
            dismiss();
        }
    }
}
