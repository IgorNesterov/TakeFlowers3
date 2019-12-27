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

    public double v1;

    public double v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);


        MyDBHelper db = new MyDBHelper(this);
        db.createDefaultShopsIfNeed();
        String par = getIntent().getStringExtra("name");
        Shop shop =  db.getShop(par);
        setPar(par);
        adreesValue = findViewById(R.id.addressText);
        adreesLabel = findViewById(R.id.addressLabel);
        telephoneValue = findViewById(R.id.telephoneText);
        telephoneLabel = findViewById(R.id.telephoneLabel);
        nameValue = findViewById(R.id.nameText1);

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

    public void setPar(String in){
        switch (in){
            case "Бутоника":{
                v1=-53.206994;
                v2=50.127627;
                break;
            }
            case "Твой букет":{
                v1=-53.194438;
                v2=50.094736;
                break;
            }
            case "Анемон":{
                v1=-53.271887;
                v2=50.257982;
                break;
            }
            case "Самцветок":{
                v1=-53.179780;
                v2=50.086766;
                break;
            }
            case "Flowers":{
                v1=-53.180729;
                v2=50.086357;
                break;
            }
            default:{
                v1=-53.209130;
                v2=50.174104;
                break;
            }
        }
    }
    public void onMapClick(View view) {

        Intent i;
        //i = new Intent(this, ListShops.class);
        i = new Intent(this, ActivityMap1.class);
        i.putExtra("v1", v1);
        i.putExtra("v2", v2);
        startActivity(i);
    }
}

