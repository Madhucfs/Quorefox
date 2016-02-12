package com.cfgb.loginpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnFocusChangeListener;
import android.widget.Toast;


public class Activity2 extends Activity {
    EditText field3, field4, field5,field6;
    String num3, num4, num5,num6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText ET = (EditText) findViewById(R.id.regpass);
        final EditText ET2 = (EditText) findViewById(R.id.regpa);
        final Button button3 = (Button) findViewById(R.id.regsignin);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View z) {
                Context m = getBaseContext();
                tabledata cru = new tabledata(m);
                field3 = (EditText) findViewById(R.id.regid);
                field4 = (EditText) findViewById(R.id.regpa);
                field5 = (EditText) findViewById(R.id.regpass);
                num3 = field3.getText().toString();
                num4 = field4.getText().toString();
                num5 = field5.getText().toString();
                cru.register(num3,num4);

               // cru.insertuser(num3, num4, num5);
                Intent myIntent = new Intent(z.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        ET.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
                field4 = (EditText) findViewById(R.id.regpa);
                num4 = field4.getText().toString();
                String myText = ET.getText().toString();
                Context m = getBaseContext();
                if (myText.equals(num4)) {
                    Toast.makeText(m, "passwords match", Toast.LENGTH_SHORT).show();
                }
            }


        });
        ET2.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View arg0, boolean arg1) {
                field6 = (EditText) findViewById(R.id.regid);
                num6 = field6.getText().toString();
                Context m = getBaseContext();
                if(num6.indexOf('@') == -1 || num6.indexOf('.') == -1 )
                {
                    Toast.makeText(m, "invalid id", Toast.LENGTH_SHORT).show();
                }

            }




        });

    }
}