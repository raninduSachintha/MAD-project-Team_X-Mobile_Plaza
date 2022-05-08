package com.example.mobilehousecard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PayCards extends AppCompatActivity {
    EditText cardNum,cardYear,cardCvv,cardName,search;
    Button addCard;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_via_cards);

        cardNum = findViewById(R.id.add_cardnum);
        cardYear = findViewById(R.id.add_monyear);
        cardCvv = findViewById(R.id.add_cvv);
        cardName = findViewById(R.id.add_name);
        addCard = findViewById(R.id.bt_paynow);
        search = findViewById(R.id.add_cardnum3);

        databaseReference = FirebaseDatabase.getInstance().getReference("cards");

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cNum = cardNum.getText().toString().trim();
                String cYear = cardYear.getText().toString().trim();
                String cCvv = cardCvv.getText().toString().trim();
                String cName = cardName.getText().toString().trim();


                        if (!TextUtils.isEmpty(cNum)) {
                            if (!TextUtils.isEmpty(cYear)) {
                                if (!TextUtils.isEmpty(cCvv)) {
                                    if (!TextUtils.isEmpty(cName)) {


                                        Card card = new Card(cNum, cYear, cCvv, cName);

                                        databaseReference.child(cNum).setValue(card);
                                        Toast.makeText(com.example.mobilehousecard.PayCards.this, "Card Details filled Sucessfully", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(getApplicationContext(), PaymentMethod.class);
                                        intent.putExtra("CNum", cardNum.getText().toString());
                                        intent.putExtra("CYear", cardYear.getText().toString());
                                        intent.putExtra("CCvv", cardCvv.getText().toString());
                                        intent.putExtra("CName", cardName.getText().toString());
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(com.example.mobilehousecard.PayCards.this, "Please Card Name", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(com.example.mobilehousecard.PayCards.this, "Please Enter Year ", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(com.example.mobilehousecard.PayCards.this, "Please Enter Cvv", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(com.example.mobilehousecard.PayCards.this, "Please Enter Card Name", Toast.LENGTH_LONG).show();
                        }

                    }


        });
    }

    public void PaymentMethods(View view) {

        Intent intent = new Intent(PayCards.this, PaymentMethod.class);
        startActivity(intent);
    }

    public void SearchCard(View view) {
        databaseReference = FirebaseDatabase.getInstance().getReference("cards");
        final String user = search.getText().toString().trim();
        Query checkUser = databaseReference.orderByChild("CNum").equalTo(user);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    search.setError(null);
                    String cardnum = dataSnapshot.child(user).child("CNum").getValue(String.class);
                    String cYear = dataSnapshot.child(user).child("CYear").getValue(String.class);
                    String cvv = dataSnapshot.child(user).child("CCvv").getValue(String.class);
                    String cName = dataSnapshot.child(user).child("CName").getValue(String.class);
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.putExtra("CNum", cardnum);
                    intent.putExtra("CYear", cYear);
                    intent.putExtra("CCvv", cvv);
                    intent.putExtra("CName", cName);


                  String  cardnum1 = intent.getStringExtra("CNum");
                  String  cYear2 = intent.getStringExtra("CYear");
                    String cvv1 = intent.getStringExtra("CCvv");
                    String cName1 = intent.getStringExtra("CName");




                    cardNum.setText(cardnum1);
                    cardYear.setText(cYear2);
                    cardCvv.setText(cvv1);
                    cardName.setText(cName1);

                } else {

                    search.setError("Try again");
                    search.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
