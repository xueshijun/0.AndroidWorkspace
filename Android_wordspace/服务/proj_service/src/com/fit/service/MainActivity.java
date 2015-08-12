package com.fit.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,Myservice2.class);
				
				int data = (int) (Math.round((Math.random() * 6))) + 5;
				
				intent.putExtra("cnt",data);
				
				startService(intent);
			}
		});
    }
}