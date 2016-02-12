package com.cfgb.loginpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    EditText field1, field2, field3, field4;
    private String num, num2, num3, num4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context m = getApplicationContext();
        final tabledata cru = new tabledata(m);
        final Button button = (Button) findViewById(R.id.signin);
        final Button button2 = (Button) findViewById(R.id.reg);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                field1 = (EditText) findViewById(R.id.email);
                field2 = (EditText) findViewById(R.id.password);

                num = field1.getText().toString();
                num2 = field2.getText().toString();


                cru.insertuser();
                // cru.insertuser(num3,num4);
                //  Log.d("IN  ONCLICK", num);
                // Log.d("In ONCLICK", num2);
                cru.validate(num, num2);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);

            }
        });





        }}