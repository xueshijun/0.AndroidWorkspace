package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Activity2 extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) 
	  {  
	    super.onCreate(savedInstanceState); 
	    
	    /* 发出Toast */
	    Toast.makeText(Activity2.this,
	                      "模拟MSN切换登录状态的程序",
	                      Toast.LENGTH_SHORT
	                     ).show();
	    finish();
	  } 

}
