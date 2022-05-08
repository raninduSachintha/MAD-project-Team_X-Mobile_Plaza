package com.example.mobilehousecard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class View_item extends AppCompatActivity {

    TextView iName;
    TextView iPrice;
    EditText iNote;
    ImageView imageView;

    Button order_btn;

    private String key;
    private String IName;
    private String IPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);
        getSupportActionBar().hide();

        key=getIntent().getStringExtra("key");
        IName =getIntent().getStringExtra("IName");
        IPrice =getIntent().getStringExtra("IPrice");

        Intent intent=new Intent();
        iName =(TextView) findViewById(R.id.textView3);
        iName.setText(IName);
        iPrice =(TextView) findViewById(R.id.textView5);
        iPrice.setText(IPrice);
        imageView = findViewById(R.id.imageView5);
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Bitmap");
        imageView.setImageBitmap(bitmap);

        order_btn =(Button)findViewById(R.id.addcart);

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item=new Item();
                item.setItemName(iName.getText().toString());
                item.setItemPrice(iPrice.getText().toString());

                Intent intent = new Intent(View_item.this, DeliveryAdd.class);
                startActivity(intent);

            }
        });




    }



}
