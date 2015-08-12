package com.fnst.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SampleActivity extends Activity {

	Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn=(Button)findViewById(R.id.entry);
        btn.setOnClickListener(new OnClickListener(){
        	public void onClick(View view){
        		Intent intent=new Intent();
        		intent.setClass(SampleActivity.this,Least.class);
        		startActivity(intent);
        	} 
        }); 
    }
}