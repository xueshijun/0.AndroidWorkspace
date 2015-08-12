package com.fit.demo;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;

public class MyActivity extends Activity {
    /** Called when the activity is first created. */
	  final StringBuffer buffer=new StringBuffer();

    //Êý×Ö¼àÌý°´Å¥
    public void show(final Button button1,final EditText text1){
    	button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				buffer.append(button1.getText().toString());
				text1.setText(buffer);
				
			}
		});
    }
    	 //·ûºÅ¼àÌý°´Å¥
        public void show2(final Button button2,final EditText text2){
        	button2.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				buffer.append(button2.getText().toString());
    				text2.setText(buffer);
    				
    			}
    		});
    }
    public float add(float a,float c){
    	float result=a+c;
    	return result;
    }
    
    
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText textShow=(EditText) findViewById(R.id.input);
        
        final Button button1=(Button) findViewById(R.id.button1);
        show(button1,textShow);
        final Button button2=(Button) findViewById(R.id.button2);
        show(button2,textShow);
        final Button buttone=(Button) findViewById(R.id.buttone);
        show(buttone,textShow);
        final Button buttona=(Button) findViewById(R.id.buttona);
        show(buttona,textShow);
        
    
//        button1.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				String button=button1.getText().toString();
//				buffer.append(button);
//				textShow.setText(buffer);
//			}
//		}); 
        
    }
}