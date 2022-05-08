package com.example.mobilehousecard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class updateDelivery extends AppCompatActivity {

    EditText pPhone,pAddress;
    TextView pName;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        data = FirebaseDatabase.getInstance().getReference("delivery");
        pName = findViewById(R.id.cus_name2);
        pPhone = findViewById(R.id.cus_no3);
        pAddress = findViewById(R.id.cus_address2);

        String name =getIntent().getStringExtra("name");
        pName.setText(name);
        String phone =getIntent().getStringExtra("phone");
        pPhone.setText(phone);
        String address =getIntent().getStringExtra("address");
        pAddress.setText(address);

    }


    public void UpdateDelivery(View view) {

        String name = pName.getText().toString().trim();
        String phone = pPhone.getText().toString().trim();
        String address = pAddress.getText().toString().trim();

        com.example.mobilehousecard.Delivery delivery=new com.example.mobilehousecard.Delivery(name,phone,address);
        data.child(name).setValue(delivery);
        Toast.makeText(this, "Delivery Updated Successfully", Toast.LENGTH_LONG).show();
    }

    public void Delete(View view) {
        String name = pName.getText().toString();
        data.child(name).setValue(null);
        Toast.makeText(getApplicationContext(), "Delivery Cancelled", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(updateDelivery.this, com.example.mobilehousecard.DeliveryAdd.class);
        startActivity(intent);

    }

    public void AddPay(View view) {
        Intent intent = new Intent(updateDelivery.this, Checkout.class);
        startActivity(intent);
    }
}