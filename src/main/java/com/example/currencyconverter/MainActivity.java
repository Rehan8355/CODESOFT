package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Spinner sp1,sp2;
    EditText ed1;
    Button b1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ed1=findViewById(R.id.txtamount);
         sp1=findViewById(R.id.spfrom);
         sp2=findViewById(R.id.spto);
         b1=findViewById(R.id.btn1);



        String []from={"USD"};
        ArrayAdapter ad=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sp1.setAdapter(ad);;

        String []to={"Indian Rupees","Shri lankan Rupees"};
        ArrayAdapter ad1=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sp2.setAdapter(ad1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double tot;
                Double amount=Double.parseDouble(ed1.getText().toString());
                if(sp1.getSelectedItem().toString()=="USD"&& sp2.getSelectedItem().toString()=="Indian Rupees"){
                    tot=amount*70.0;
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                } else if (sp1.getSelectedItem().toString()=="USD"&&sp2.getSelectedItem().toString()=="Shri lankan Rupees") {
                    tot=amount*100.0;
                    Toast.makeText(getApplicationContext(),tot.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}