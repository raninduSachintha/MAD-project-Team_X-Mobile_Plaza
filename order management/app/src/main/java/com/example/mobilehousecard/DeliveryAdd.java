package com.example.mobilehousecard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class DeliveryAdd extends AppCompatActivity {

    EditText pName,pPhone,pAddress;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        data = FirebaseDatabase.getInstance().getReference("delivery");

        pName = findViewById(R.id.cusname);
        pPhone = findViewById(R.id.cusphone);
        pAddress = findViewById(R.id.cus_no3);

    }

    public void UpdateDelivery(View view) {

    }

    public void UpdateDeli(View view) {

        String name = pName.getText().toString().trim();
        String phone = pPhone.getText().toString().trim();
        String address = pAddress.getText().toString().trim();

        if (name.isEmpty()|| phone.isEmpty()||address.isEmpty()){
            Toast.makeText(this, "Please Type every fields", Toast.LENGTH_LONG).show();
        }else{
            com.example.mobilehousecard.Delivery delivery=new com.example.mobilehousecard.Delivery(name,phone,address);
            data.child(name).setValue(delivery);
            Toast.makeText(this, "Delivery Added Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(com.example.mobilehousecard.DeliveryAdd.this, updateDelivery.class);
            intent.putExtra("name", pName.getText().toString());
            intent.putExtra("phone", pPhone.getText().toString());
            intent.putExtra("address", pAddress.getText().toString());
            startActivity(intent);
        }

    }
}