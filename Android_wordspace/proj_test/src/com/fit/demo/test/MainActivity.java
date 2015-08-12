package com.fit.demo.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button submit=(Button) findViewById(R.id.submit);
        final EditText name=(EditText) findViewById(R.id.name);
        final EditText pass=(EditText) findViewById(R.id.pass);
        
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle extras=new Bundle();
				extras.putString("name",name.getText().toString());
				extras.putString("pass",pass.getText().toString());
				
				Intent intent=new Intent(MainActivity.this, Activity1.class);
				intent.putExtras(extras);
				startActivity(intent);
				
				
			}
		});
    }
}