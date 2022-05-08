package com.example.mobileplaza;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button move;        //gets button packge and create varibale any  variable, move
    private Button go;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //first xml file we create

        move=findViewById(R.id.btn_pay);      //move-prevoius declaed variable btn find by id,give btn id for that
        move.setOnClickListener(new View.OnClickListener() {          //method when on click// type O for after new auto override code
            @Override
            public void onClick(View view) {       //use onclick method
                Intent intent=new Intent(MainActivity.this,carddetails.class);       //create new object intent,MainActivity.this-now in function, carddetails.class-where go after click function
                startActivity(intent);         //run intent object in startActivity function
            }
        });

        go=findViewById(R.id.btn_cash);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents=new Intent(MainActivity.this,successful.class);
                startActivity(intents);
            }
        });
    }




}