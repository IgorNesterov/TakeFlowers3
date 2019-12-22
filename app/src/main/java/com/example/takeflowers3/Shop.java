package com.example.takeflowers3;

import java.io.Serializable;

public class Shop implements Serializable {

    private int shopId;
    private String shopName;
    private String shopAddress;
    private String shopTelephone;
    private int dostavka;

    public Shop()  {

    }

    public Shop(String _shopName, String _shopAddress, String _shopTelephone, int _dostavka) {
        this.shopName = _shopName;
        this.shopAddress = _shopAddress;
        this.shopTelephone= _shopTelephone;
        this.dostavka = _dostavka;
    }

    public Shop(int _shopId, String _shopName, String _shopAddress, String _shopTelephone, int _dostavka) {
        this.shopId = _shopId;
        this.shopName = _shopName;
        this.shopAddress = _shopAddress;
        this.shopTelephone= _shopTelephone;
        this.dostavka= _dostavka;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int _shopId) {
        this.shopId = _shopId;
    }
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String _shopName) {
        this.shopName = _shopName;
    }


    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String _shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopTelephone() {
        return shopTelephone;
    }

    public void setShopTelephone(String _shopTelephone) {
        this.shopTelephone = _shopTelephone;
    }

    public int getDostavka() {
        return dostavka;
    }

    public void setDostavka(int dostavka) {
        this.dostavka = dostavka;
    }


    @Override
    public String toString()  {
        return this.shopName;
    }

}
