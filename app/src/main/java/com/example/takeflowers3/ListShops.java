package com.example.takeflowers3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListShops extends AppCompatActivity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 700;

    private ListView listView;

    private static final int MY_REQUEST_CODE = 1000;

    public List<Shop> shopList = new ArrayList<Shop>();
    public ArrayAdapter<Shop> listViewAdapter;

    //private boolean needRefresh=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shops2);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);

        MyDBHelper db = new MyDBHelper(this);
        db.createDefaultShopsIfNeed();



        List<Shop> list = db.getAllShops();
        this.shopList.addAll(list);


        this.listViewAdapter = new ArrayAdapter<Shop>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.shopList);


        // Assign adapter to ListView
        this.listView.setAdapter(this.listViewAdapter);

        // Register the ListView for Context menu
        registerForContextMenu(this.listView);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        final AppCompatActivity obj = this;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента

                //if(strText.equalsIgnoreCase(getResources().getString(R.string.name1))) {
                Intent i;
                i = new Intent(obj, ElementActivity.class);
                i.putExtra("name", strText);
                startActivity(i);
                // }
            }
        });


    }

    // When AddEditNoteActivity completed, it sends feedback.
    // (If you start it using startActivityForResult ())
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            // Refresh ListView
            if (needRefresh) {
                this.shopList.clear();
                MyDBHelper db = new MyDBHelper(this);
                List<Shop> list = db.getAllShops();
                this.shopList.addAll(list);

                // Notify the data change (To refresh the ListView).
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }

    public List<Shop> mixList() {
        List<Shop> tmp1 = this.shopList;
        List<Shop> tmp2 = this.shopList;
        tmp2.clear();
        int listSize = tmp1.size();
        for (int i = 0; i < listSize; i++) {
            int x = 0 + (int) (Math.random() * (listSize + 1)) - 1;

            tmp2.add(tmp1.get(x));
            tmp1.remove(x);
            listSize--;
        }
        return tmp2;
        //this.listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    this.shopList.clear();
                    MyDBHelper db = new MyDBHelper(this);
                    List<Shop> list = db.getAllShops();
                    this.shopList.addAll(list);

                    // Notify the data change (To refresh the ListView).
                    this.listViewAdapter.notifyDataSetChanged();

                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
