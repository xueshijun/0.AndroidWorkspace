package com.fit.web;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String url="http://192.168.100.114:8080/proj_myweb/file/list.xml";
        ConnectionDAO.saveFile(url);
    }
}