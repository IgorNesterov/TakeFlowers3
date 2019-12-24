package com.example.takeflowers3;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ElementActivity extends AppCompatActivity {
    TextView adreesValue;
    TextView adreesLabel;
    TextView telephoneValue;
    TextView telephoneLabel;
    TextView nameValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);


        MyDBHelper db = new MyDBHelper(this);
        db.createDefaultShopsIfNeed();
        String par = getIntent().getStringExtra("name");
        Shop shop =  db.getShop(par);

        adreesValue = findViewById(R.id.addressText);
        adreesLabel = findViewById(R.id.addressLabel);
        telephoneValue = findViewById(R.id.telephoneLabel);
        telephoneLabel = findViewById(R.id.addressLabel);
        nameValue = findViewById(R.id.addressLabel);

        adreesLabel.setText(R.string.addressLabel);
        telephoneLabel.setText(R.string.telephoneLabel);

        adreesValue.setText(shop.getShopAddress());
        telephoneValue.setText(shop.getShopTelephone());
        nameValue.setText(shop.getShopName());

    }

    public void onCallClick(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telephoneValue));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onMapClick(View view) {
    }
}

