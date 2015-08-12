package com.fit.complete;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        AutoCompleteTextView auto=(AutoCompleteTextView) findViewById(R.id.auto);
        
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.auto, getResources().getStringArray(R.array.complete));
        auto.setAdapter(adapter);
        
    }
}