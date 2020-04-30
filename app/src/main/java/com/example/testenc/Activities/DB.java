package com.example.testenc.Activities;

public class DB {

    private static DB instance;

    private String KEY = "zzxxccvvbbnnmmllkkjjhhggffddssaa";
    private String IV = "";
    private String TEXT = "";
    private String ENC_TEXT = "";

    public static synchronized DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public String getKEY() {
        return KEY;
    }

    public String getIV() {
        return IV;
    }

    public void setIV(String IV) {
        this.IV = IV;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public String getENC_TEXT() {
        return ENC_TEXT;
    }

    public void setENC_TEXT(String ENC_TEXT) {
        this.ENC_TEXT = ENC_TEXT;
    }
}
