package com.example.mobileplaza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class carddetails extends AppCompatActivity {

    EditText et_cardname;
    EditText et_cardnumber;     //make references for input data,refernce name for any
    EditText et_cardexp;
    EditText et_cardcvv;

    private Button move;
    private Button btnView;
    Button btn_add, btnUpdate, btnDelete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carddetails);


        move=findViewById(R.id.btn_paymethods);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(carddetails.this,mycards.class);
                startActivity(intent);
            }
        });
        btnView=findViewById(R.id.btn_paying);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(carddetails.this,successful.class);
                startActivity(intent);
            }
        });



    }
}