package com.example.takeflowers3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final String DATABASE_NAME = "DataBase.db";
    private static final int DATABASE_VERSION = 3;

    public MyDBHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_NAME = "shop";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_DOSTAVKA = "dost";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.i(TAG, "MyDBHelper.onCreate ... ");
        // Script to create table.
        String script = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_TELEPHONE + " TEXT," + COLUMN_DOSTAVKA + " INTEGER" +  ")";
        // Execute script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDBHelper.onUpgrade ... ");
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Recreate
        onCreate(db);
    }

    // If Note table has no data
    // default, Insert 2 records.
    public void createDefaultShopsIfNeed()  {
        int count = this.getShopsCount();
        if(count ==0 ) {
            Shop shop1 = new Shop("Твой букет","ул. Красноармейская 5","89277071234",1);
            Shop shop2 = new Shop("Анемон","Московское ш. 10","89277071934",1);
            Shop shop3 = new Shop("Самцветок","ул. Фрунзе 15","89277072234",0);
            Shop shop4 = new Shop("Flowers","ул. Фрунзе 4","89277079234",0);
            Shop shop5 = new Shop("Бутоника","ул. Ново-садовая 8","89277071200",1);
            this.addShop(shop1);
            this.addShop(shop2);
            this.addShop(shop3);
            this.addShop(shop4);
            this.addShop(shop5);
        }
    }


    public void addShop(Shop _shop) {
        Log.i(TAG, "MyDBHelper.addShop ... " + _shop.getShopName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, _shop.getShopName());
        values.put(COLUMN_ADDRESS, _shop.getShopAddress());
        values.put(COLUMN_TELEPHONE, _shop.getShopTelephone());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);

        // Closing database connection
        db.close();
    }


    public Shop getShop(String name) {
        Log.i(TAG, "MyDBHelper.getShop ... " + name);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,
                        COLUMN_NAME, COLUMN_ADDRESS, COLUMN_TELEPHONE, COLUMN_DOSTAVKA }, COLUMN_NAME + "=?",
                new String[] { String.valueOf(name) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Shop shop = new Shop(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getInt(4));
        // return note
        return shop;
    }


    public List<Shop> getAllShops() {
        Log.i(TAG, "MyDBHelper.getAllShops ... " );

        List<Shop> shopList = new ArrayList<Shop>();
        // Select All Query
        int x = (int)(Math.random()*10);
        String par;
        if(x<3){
            par=COLUMN_NAME;
        }
        else {
            if(x<5){
                par=COLUMN_TELEPHONE;
            }
            else {
                if(x<7){
                    par=COLUMN_ADDRESS;
                }
                else{
                    par=COLUMN_ID;
                }
            }
        }

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ par;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop();
                shop.setShopId(Integer.parseInt(cursor.getString(0)));
                shop.setShopName(cursor.getString(1));
                shop.setShopAddress(cursor.getString(2));
                shop.setShopTelephone(cursor.getString(3));
                shop.setDostavka(cursor.getInt(3));
                // Adding note to list
                shopList.add(shop);
            } while (cursor.moveToNext());
        }

        // return note list
        return shopList;
    }

    public int getShopsCount() {
        Log.i(TAG, "MyDBHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

}