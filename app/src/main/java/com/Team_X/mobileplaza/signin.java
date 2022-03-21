package com.Team_X.mobileplaza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //admin and admin123

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin123"))
                    Toast.makeText(signin.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(signin.this,"LOGIN unSUCCESSFUL",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void openSignup(View view) {
        startActivity(new Intent(this,signup.class));
    }
}